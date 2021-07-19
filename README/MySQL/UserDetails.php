<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"rentasavari");
$email = $_POST["email"];
$qry="select * from users where email = '$email'";
$raw=mysqli_query($conn,$qry);

while($res=mysqli_fetch_array($raw))
{
    $data[]=$res;
}
print(json_encode($data));
?>