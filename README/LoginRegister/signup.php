<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email']) && isset($_POST['phone_no1']) && isset($_POST['password']) && isset($_POST['license_no'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("users", $_POST['first_name'], $_POST['last_name'], $_POST['email'], $_POST['phone_no1'], $_POST['password'], $_POST['license_no'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
