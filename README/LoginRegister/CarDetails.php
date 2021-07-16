<?php
    define('DB_HOST','localhost');
    define('DB_USER','root');
    define('DB_PASSWD','');
    define('DB_NAME','rentasavari');
    
    $conn = new mysqli(DB_HOST, DB_USER, DB_PASSWD, DB_NAME);
    if(mysqli_connect_errno())
    {
        die('Unable To Connect Database'.mysqli_connect_error());
    }
    
    $stmt = $conn->prepare("select id,car_name,car_image,car_no_plate,body_type,seat_capacity,transmission_type,fuel_type,rent_price,booking_status,car_status from cars;");
    $stmt->execute();
    $stmt->bind_result($id,$car_name,$car_image,$car_no_plate,$body_type,$seat_capacity,$transmission_type,$fuel_type,$rent_price,$booking_status,$car_status);
    $car = array();
    while($stmt->fetch())
    {
        $temp = array();
        $temp['id'] = $id;
        $temp['car_name'] = $car_name;
        $temp['car_image'] = $car_image;
        $temp['car_no_plate'] = $car_no_plate;
        $temp['body_type'] = $body_type;
        $temp['seat_capacity'] = $seat_capacity;
        $temp['transmission_type'] = $transmission_type;
        $temp['fuel_type'] = $fuel_type;
        $temp['rent_price'] = $rent_price;
        $temp['booking_status'] = $booking_status;
        $temp['car_status'] = $car_status;
        array_push($car,$temp);
    }
    echo json_encode($car);
