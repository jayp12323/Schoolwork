#!perl
#	nodes-verify.pl
#
# Validates if nodes line up with the lines on the display
#
# To use:
#	perl nodes_verify.pl full_display_name
#		
# Output file will be nodes_verified_filename.txt. 
#	
#	History:
#
#	14-July-2011	[jmp]	Initial version
#	18-July-2011	[jmp]	Modified for use in run_check_dset.bat

$txt = $ARGV[0];
@input_name = split /_/,$txt;						#used for naming the output file
$input_name = join "_", @input_name[$#input_name-1..$#input_name]; 	#used for naming the output file
$output_file  = "nodes_verified_".$input_name;
$output_filex = ">>".$output_file;
$x = system("del \"$output_file\"");					#deletes output file if it already exists
open (output,$output_filex) || die ">>>cant open $output_file file";
open (text,"<$txt");

while ($dset =<text>)							#this loop goes through the network/station text files and checks the for dset names
{
	chomp ($dset);
	$dset =~ s/^ //g;
	if ($dset=~ /dset/i & $dset !~ /^!/i )				#if the line contains "dset"
	{
		@input = split / /,$dset;
		$input = $input[1];					#The input is the dset name
		print output "\n\n------$input------";	
		&nodes_verify;						#run the nodes verify subroutine
	}
}
close(output);
sub nodes_verify
{
$input_file   = $input."_dset.ddl";
$input_filex  = "<".$input_file;

$error = 0;
$num=0;
@name = 0;

open (input, $input_filex)  || die ">>>cant open $input_file file";
while ($line = <input>)
{
	chomp ($line);
	$line =~ s/\s{2,}//g; 			#removes whitespaces >= 2 characters in length
	$line =~ s/\(/ /g;			#changes parentheses into space
	$line =~ s/\)/ /g;			#changes parentheses into space
	$line =~ s/"//g;			#removes quotations
	
	if ($line =~ /^\s+display/i)		#searches for lines that have "display" in them, giving the name of the line diagram
	{
		close(horz);
		close(vert);
		close(nodes);

		@disp = split / /, $line;	# splits the line
		$disp = $disp[1];		#pulls the name from the line
		$name[$num] = $disp;		#creates an array with the display names in it
		$num = $num+1;			#used for incrementing array
		$vert = $disp."_vert.txt";	#this and next two lines are used for naming txt files that will contain nodes and polylines
		$horz = $disp."_horz.txt";
		$nodes = $disp."_nodes.txt";
	
		open (vert,">$vert") || die ">>>cant open $vert file";		
		open (horz,">$horz") || die ">>>cant open $horz file";
		open (nodes,">$nodes") || die ">>>cant open $nodes file";
	}
	&find;					#runs the find subroutine
}
close(horz);
close(vert);
close(nodes);
foreach $displ (@name)				#used the array with the names of the displays
{
	$willprint = 1;
	$vertl = $displ."_vert.txt";		#used to open up 
	$horzl = $displ."_horz.txt";
	$nodes = $displ."_nodes.txt";
	open (nodesl,"<$nodes") || die ">>>cant open $nodes file";

	&verify;				#runs the verify subroutine
	close(nodesl);
	system("del \"$horzl\"");
	system("del \"$nodes\"");
	system("del \"$vertl\"");
}
print "there were $error errors in $input\n";
close(input);

}
sub find
{
	if( $line =~/polyline/)
	{
		$poly=1; #found polyline
	}

	elsif($line =~/point/ & $poly==1)		#if the line contains "point" and it found it found "polyline" then this point is the start of the polyline
	{	
		$point=1;				#found point
		$poly=0;				#resets polyline
		@start = split / /, $line;
		@start = @start[1,2];			# creates array with starting coordinates
		
	}
	
	elsif($line =~/point/ & $point==1)		#if the line contains "point" and there already is a starting point, then this is the ending point
	{
		$point=0;	#resets point
		@end = split / /, $line;
		@end = @end[1,2];			# Not the ending coordinates, but how much the x and y coordinates change, for example 0,-10 would me it goes down 10
		if ($end[0]<3 & $end[0]>-3)		#if the first value of the starting coordinates is roughly zero, then it primarily changes in vertically, so it's a vertical line
		{
			@verta = (@start[0],@start[1],@end[0]+@start[0],@end[1]+@start[1]);	#adds starting coordinates and the change of coordinates together 
			print vert "@verta\n";						#prints to txt file names after display_vert which contains vertical coordinates of polylines
		}
		else	#if not a vertical line, then it must be a horizontal line

		{
			@horz = (@start[0],@start[1],@end[0]+@start[0],@end[1]+@start[1]);	#adds starting coordinates and the change of coordinates together 
			print horz "@horz\n";						#prints to txt file names after display_horz which contains vertical coordinates of polylines
		}	
	}
	
	elsif( $line =~/NODE_orange/i | $line =~/NODE_ON_NODE_LAYER/i)			#looks for node
	{
		$foundnode=1; #found node
	}
	
	elsif($line =~/origin/ & $foundnode==1)			#looks for node coordinates
	{
		$foundnode=0;
		@node = split / /, $line;			#puts node coordinates into an array
		@node = ($node[1]+10,$node[2]+10);		#adds 10 to both coordinates to be the location of the center of the node, not the upper left corner
		print nodes "@node\n";				#prints the coordinates to the display_nodes txt file
	}
}

sub verify
{
	while ($line2 = <nodesl>)
	{
		open (vertl,"<$vertl") || die ">>>cant open $vertl file";
		open (horzl,"<$horzl") || die ">>>cant open $horzl file";
		$noterror=0;							# resets the noterror variable.
		$ishorz=0;							#resets the ishorz variable
		chomp ($line2);
		@node = split / /, $line2;					#splits the lines in the nodes txt file into an array
		foreach $horz (<horzl>)
		{
			chomp ($horz);
			@horz = split / /, $horz;				#splits the coordinates in the horz file into an array
			if ($horz[0]>$horz[2])					#if the first x coordinate is bigger than the second one this loop switches them
			{
				$temp = $horz[0];
				$horz[0] = $horz[2];
				$horz[2] = $temp;
			}		

			if  ($horz[1]-12<$node[1]&$node[1]<$horz[1]+12)		#if the y coordinate of the node is within +/- 12 of the y coordinate of any line
			{

				if  ($horz[0]-12<$node[0]&$node[0]<$horz[2]+12)	# and if the x coordinate of the node is between the start and end x coordinates of a line, +/- 12
				{
					$ishorz=1;				#is set when the node is on a horizontal line, this skips the check for whether it is on a vertical line
					$noterror=1;				#is set to show that node is on a line, therefore not an error
				}
			}

		}
		unless ($ishorz==1)				#unless it was shown that the node was on a horizontal line above, then this will check to see if its on a vertical line
		{
			while ($vert = <vertl>)
			{
				chomp ($vert);
				@vert = split / /, $vert;			#splits the coordinates in the vert file into an array
				if ($vert[1]>$vert[3])				#if the first y coordinate is bigger than the second one this loop switches them
				{
					$temp = $vert[1];
					$vert[1] = $vert[3];
					$vert[3] = $temp;
				}
				if  ($vert[0]-12<$node[0]&$node[0]<$vert[0]+12)		#if the x coordinate of the node is within +/- 12 of the x coordinate of any line
				{
					if  ($vert[1]-12<$node[1]&$node[1]<$vert[3]+12)	# and if the y coordinate of the node is between the start and end y coordinates of a line, +/- 12
					{
						$noterror=1;				#is set to show that node is on a line, therefore not an error
					}
				}
			}
		}
		if ($noterror==0 & $willprint == 1)
		{
			print output "\n\nIn $displ, there were errors with nodes at the following coordinates";
			$willprint = 0;
		}

		$error = $error + 1 if $noterror==0;					#used to print the number of errors this scrip has found
		@node=($node[0]-10,$node[1]-10);					#used to print the coordinates of the node if it is not found to be on a line
		print output "\n$node[0],$node[1]" if $noterror==0;			#prints the coordinates of the node if it is not found to be on a line

		close(horzl);
		close(vertl);
		
	}


}
