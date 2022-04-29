<?php
$con=mysqli_connect("192.168.1.11","ma","ma","madb","3306");
mysqli_query($con,'SET NAMES utf8');

$user_id = $_POST["user_id"];

$sql = "SELECT user_id, manager_id, request_title,u.classify_name,request_date,request_state from user_request u join approval_classify a on u.classify_name = a.classify_name where user_id=?   order by request_number desc";

$result = mysqli_prepare($con,$sql);

mysqli_stmt_bind_param($result, "s", $user_id);
mysqli_stmt_execute($result);


mysqli_stmt_store_result($result);
mysqli_stmt_bind_result($result,$user_id,$manager,$title,$category,$rdate,$state);



$response = array();
$response["success"] = false;

if($result){  
while($row=mysqli_stmt_fetch($result)) {
    
    $response["success"] = true;
    array_push($response,
    array('user_id'=>$user_id,
         'manager'=>$manager,
         'title'=>$title,
         'category'=>$category,
         'rdate'=>$rdate,
         'state'=>$state
     )); 
    // array('user_id'=>$row['user_id'],
    //     'manager'=>$row['manager_id'],
    //     'title'=>$row['request_title'],
    //     'category'=>$row['classify_name'],
    //     'rdate'=>$row['request_date'],
    //     'state'=>$row['request_state']
    // )); 
}

echo json_encode($response,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
}
else{
    echo "SQL문 처리중 에러 발생 : ";
    echo mysqli_error($con);
    }
    

?>

