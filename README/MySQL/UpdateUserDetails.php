<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"rentasavari");
$first_name = $_POST["first_name"];
$last_name = $_POST["last_name"];
$phone_no1 = $_POST["phone_no1"];
$phone_no2 = $_POST["phone_no2"];
$email = $_POST["email"];
$password = $_POST["password"];
$password = password_hash($password, PASSWORD_DEFAULT);
$license_no = $_POST["license_no"];
$address = $_POST["address"];
$area = $_POST["area"];
$city = $_POST["city"];
$state = $_POST["state"];
$pincode = $_POST["pincode"];

$qry = "UPDATE users SET first_name = '$first_name', last_name = '$last_name', phone_no1 = '$phone_no1', phone_no2 = '$phone_no2', password = '$password', license_no = '$license_no', address = '$address', area = '$area', city = '$city', state = '$state', pincode = '$pincode' WHERE email = '$email' ";
$raw=mysqli_query($conn,$qry);

if($raw)
{
    echo "Profile Updated";
}
else
{
    echo "Failed To Update Profile";
}
?>