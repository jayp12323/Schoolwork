#!perl
#	log_strip.pl
#
# strips selected messages from an error file
#
# To use:
#	perl log_split.pl "(directory)\(name of log file with extension)" (Reference file containing what errors to be stripped) (output file name)
#		directory is unnecessary if log is in the same directory as this file. If a different directory, it must be in double quotes eg. ""
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
#	27-May-2011	[jmp]	Initial version

#-----------------------------------------------------------------
#open files
#-----------------------------------------------------------------
@ARGV[0] = join /\\/, @ARGV[0..$#ARGV -2];			# if the directory of the log file has spaces in the name, perl reads it as multiple arguments 
								# this line combines them into a single argument

@ARGV[0] =~ s/ /\ /;						# replaces spaces with whitespace that perl recognizes
@directory = split /\\/, "$ARGV[0]";				# splits the log file argument into directory of the log file. 
								# This is used to check whether the log file
								# is in the same location as the log
$input = "$ARGV[0]";	
# input is log file directory/name
$input_dir = $input;									#creates a new variable that will only have the directory in it
$input_dir =~ s/$directory[$#directory]//;						#pulls the directory out
$input_file  = "<"."$input";
$output_file  = "$ARGV[2]";
open (input, "$input_file")  || die ">>>cant open $input_file file";
open output, ">", "$input_dir$output_file" || die ">>>cant open $output_file file";	#creates ouput file in input file directory
	
$afile = "$ARGV[1]";
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
print output "\n$directory[$#directory]\n";

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
