#!perl
#	log_condense.pl
#
# Condenses multi-line log messages onto a single line
#
# To use:
#	perl log_condense.pl \(directory)\(name of log file with extension)
#		directory is unnecessary if log is in the same directory as this file
#		
#	History:
#
#	27-May-2011	[jmp]	Initial version
#-----------------------------------------------------------------
#open files
#-----------------------------------------------------------------
@ARGV[0] = join /\\/, @ARGV;			# if the directory of the log file has spaces in the name, perl reads it as multiple arguments 
						# this line combines them into a single argument
@ARGV[0] =~ s/ /\ /;				# replaces spaces with whitespace that perl recognizes
@directory = split /\\/, "$ARGV[0]";		# splits the log file argument into directory of the log file. 											# If they do not equal, the log file is in a different directory

$input = "$ARGV[0]";	
# input is log file directory/name
$input_name = $directory[$#directory];
$input_dir = $input;				#creates a new variable that will only have the directory in it
$input_dir =~ s/$directory[$#directory]//;	#pulls the directory out
$input_file  = "<"."$input";
$output_file = "condensed_$directory[$#directory]";
open (logfile, "$input_file")  || die ">>>cant open input $input_file file";
open output, ">", "$input_dir$output_file" || die ">>>cant open output $output_file file";	#creates ouput file in input file directory


#------------------------------------
#process the file
#-----------------------------------------------------------------

$divider = '~~';						# sets divider
$linenum=1; 							#used to keep heading lines of log file unchanged 

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
		if ($line =~ m/^\s*\d{2}\-\w{3}\-\d{4}/ )  	
		{
			print output "\n$line" 			#If the line starts with a date, prints it on a new line
		}
		elsif ($line !~ m/\w/) {}
		else 
		{	
			print output "$divider$line"; 		# If the line begins with something besides a date, print it on the same line as the date, 
								# and divide it with what is set as the variable named "divider"
		}
	}
}

close (input);
close (output);
