#@in = <STDIN>;
$in = $ARGV[0];

# $num = @in[0];
# shift(@in);
$pos = "first";
open (input, "$in");
foreach $line (<input>)
{
#foreach $line(@in) {
	chomp($line);
	
    if($line !~ m/\w/){
	next;
    }
	
   elsif($pos eq "second")
    {
      $V = $line;
        @v1 = split( //, $V);
		$sizeV = $#v1+1;
        &compare();
        $pos = "first";
        print "\n";
        next;
    }
    elsif($pos eq "first")
    {   
		$pos = "second";
        $P = $line; 
        @p1 = split(//,  $P);
		$sizeP = $#p1+1;
    }
   
	
}

sub compare{


	if($sizeV==$sizeP)
	{
		$numdiffs = 0;
		for($count=0;$count< $sizeV;$count++)
        {  
			if(!($temp[$c2] eq $v1[$c2]))
			{
				$numdiffs = $numdiffs+1 ;
			}
						
		}
		print "0" if($numdiffs<2);
	}
	else
        {
            $diff = $sizeP - $sizeV+1;
        }
        for($count=0;$count< $diff;$count++)
        {
			if($sizeV == 1)
            {
              print "$count ";   
            }
			elsif($sizeV < 7)
            {
				
				@temp = splice(@p1,$count,$diff);

            
                $numdiffs = 0;
                $c2=0;
                while($c2<$sizeV && $numdiffs<2)
                {
                
                   if(!($temp[$c2] eq $v1[$c2])){
                    $numdiffs = $numdiffs+1 ;
                    }
                    $c2++;


                }
                if($numdiffs<2){
                   print "$count "; 
                }
            }
 
        }
	# print $sizeV;

}