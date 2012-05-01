use MIME::Base64 qw(encode_base64);
$pass = 'e1NTSEF9Z2dxOUIwek9nMXRUZXlrd3YzS0hNZGZXcmlQOExXVVdNQWdjYnc9PQ=';
@input_name = split //,'P@$$word1';
for ($count=0; $count<7; $count++)
{
	@enc = @input_name[$count,$count+1,$count+2];
	$enc = join "",@enc;
	print "$enc\n";
	$encoded = encode_base64($enc);
	print "$encoded\n";
	print "match" if $pass =~ /$encoded/;
}
