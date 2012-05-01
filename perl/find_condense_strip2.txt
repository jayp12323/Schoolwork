#!perl
#	find_condense_strip.pl
#
# Condenses multi-line log messages onto a single line and strips specified messages from it
#
# To use:
#	perl find_condense_split.pl "(directory logs are in)" (Reference file containing what errors to be stripped) (output file name)
#		directory is necessary and must be in double quotes, ""
#		
# Reference file must be in same directory as this script and should have 1 error per line, for example:
#	
# 		Error to ignore 1
#		Error to ignore 2
#		...
#
# Ouput file will be located in the same directory as input file
#	
#	History:
#
#	30-May-2011	[jmp]	Initial version


@ARGV[0] = join /\\/, @ARGV[0..$#ARGV -2];			# if the directory of the log file has spaces in the name, perl reads it as multiple arguments 
								# this line combines them into a single argument

@ARGV[0] =~ s/ /\ /;						# replaces spaces with whitespace that perl recognizes
@directory = split /\\/, "$ARGV[0]";				# splits the log file argument into directory of the log file.
$input_dir = "$ARGV[0]\\";					# input is log file directory/name
$output_file  = "$ARGV[2]";					# output file name is the last argument when calling this script
&find;								# runs the find subroutine. this subroutine finds all the files with a .txt or .log extension
								# and prints their names into a text file that will be used later

open(filelist, "<filelist.txt");				# opens the text file with the names of the logs
foreach $file (<filelist>)					# for each file (a line of the text file)
{
	chomp $file;
	&condense;						# runs the condense subroutine which condenses multi-line messages into a single line
	$condensed = "condensed_".$file;				
	&strip;							# runs the strip subroutine which strips selected messages from the condensed files
	$x = system("del $condensed");				# deletes the condensed files that were created
}
close filelist;
$y = system("del filelist.txt");				#deletes the filelist

sub find
{
	opendir(cd,"$ARGV[0]")|| die "<<<error: directory not found>>>";	#opens the directory with the log files
	open (output, ">filelist.txt");	
	$x = system("del \"$input_dir$output_file\"");				#deletes the output file if present so it doesn't get added to the new output file.
										# ignore the error if applicable (if the file hasn't been created yet)
	while ($file = readdir(cd))

	{
		print output "$file\n" if $file =~ m/\.log$|.txt$/i ;		#adds the file name if it ends in .log or .txt
	}
	close output;
}

sub condense
{
	$input = "$input_dir$file";						# input is log file directory/name
	$output_name = "condensed_$file";
	open (logfile, "<$input")  || die ">>>cant open input $file file";
	open (output, ">$output_name") || die ">>>cant open output $output_name file";	#creates ouput file in input file directory


	#------------------------------------
	#process the file
	#-----------------------------------------------------------------

	$divider = '~~';						# sets divider
	$linenum=1; 							# used to keep heading lines of log file unchanged 

	foreach $line (<logfile>)
	{
		$line =~ s/ +/ /g;					# reduces multiple whitespace to a single space
		$line =~ s/^ | $//g;					# Removes leading or trailing whitespace
		$line =~ s/\n//g;  					# removes trailing line break
		if ($linenum < 2)					# Until the line starts with a date, this script prints the lines unprocessed
		{
			if ($line =~ m/^\s*\d{2}\-\w{3}\-\d{4}/)    	# If the line starts a date, the script processes and prints the line. 
			{
				$linenum = 3;				# changes the variable so the script will start processing the data
				print output "$line";
			}
			
			else
			{
			print output "$line\n";				# prints the line unprocessed if it doesn't start with a date
			}
		}
		
	#-----------------------------------------------------------------
	#Condensing
	#-----------------------------------------------------------------
		
		else												
		{		
			if ($line =~ m/^\s*\d{2}\-\w{3}\-\d{4}|^condensed|^compiling display set/ )  	
			{
				print output "\n$line" 			# If the line starts with a date, prints it on a new line
			}
			elsif ($line !~ m/\w/) {}			# If the line doesn't contain alphanumeric characters, ignore it
			else 
			{	
				print output "$divider$line"; 		# If the line begins with something besides a date, print it on the same line as the date, 
									# and divide it with what is set as the variable named "divider"
			}
		}
	}

	close (logfile);
	close (output);
}

sub strip
{
	$input = $condensed;	
	$input_file  = "<"."$input";
	open (input, "$input_file")  || die ">>>cant open $input_file file";
	open output, ">>", "$input_dir$output_file" || die ">>>cant open $output_file file";	#creates ouput file in input file directory
		
	$afile = "$ARGV[1]";		#reference file
	$afilex = "<".$afile;
	$file_error = open(afile,$afile);
	#-----------------------------------------------------------------
	#process the reference file
	#-----------------------------------------------------------------

	if(!$file_error)
	{
		print "$afile not found\n";
	}

	$num = 0;								#used when creating an array that contains the error names to be ignored
	foreach $line (<afile>)
	{
		chomp($line);			
		unless ($line !~ m/\w/)						#ignores a line if it doesn't contain any alphanumeric characters
		{
			$line =~ s/^ +| +$//g;					#removes leading and trailing whitespace
			@splits[$num] = "$line";				#an array called splits contains each error name that will be ignored
			$num = $num+1;						#used to increment array locations
		}
	}
	print output "\n$file\n";

	#-----------------------------------------------------------------
	#process the log file
	#-----------------------------------------------------------------
	while ($line = <input>)
	{
		next if ($line !~ m/\w/);			# ignores and doesn't print lines with no alphanumeric characters
		$done = 0;
		chomp ($line);
		foreach $reference (@splits)			# takes the log file line by line and checks if any of the error names to be ignored are in that line
		{
			if ($line =~ m/$reference/i)		# if it contains any of the error names, it sets "done" to be true
			{
				$done = 1
			}
		}
			if ($done==0)				# unless done is false, it prints the line
			{
				print output "$line\n" ;
			}
	}
	close(input);
	close(output);
}