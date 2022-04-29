<?php
$con=mysqli_connect("192.168.1.11","ma","ma","madb","3306");
mysqli_query($con,'SET NAMES utf8');
// $result=mysqli_query($con,"select * from user ");
// while($row = mysqli_fetch_array($result)){

// echo "아이디: ".$row['user_id']."<br/>";
// }

$user_id = $_POST["user_id"];

$user_pwd = $_POST["user_pwd"];

// $result = mysqli_prepare($con,"SELECT user_id,user_password FROM user WHERE user_id = ? AND user_password = ?");
// mysqli_stmt_bind_param($result, "ss", $user_id, $user_pwd);
// mysqli_stmt_execute($result);

// mysqli_stmt_store_result($result);
// mysqli_stmt_bind_result($result,$user_id,$user_pwd);

$result = mysqli_prepare($con,"SELECT user_id,user_name,duty_name,team,depart,head,c.code_name,current_state from usersdetail u join code_info c on u.authority_code=c.common_code WHERE user_id = ? AND user_password = ?");

mysqli_stmt_bind_param($result, "ss", $user_id, $user_pwd);
mysqli_stmt_execute($result);

mysqli_stmt_store_result($result);
mysqli_stmt_bind_result($result,$user_id,$username,$duty,$team,$depart,$head,$authority,$currentstate);

$response = array();
$response["success"] = false;

while(mysqli_stmt_fetch($result)) {
   
    // $response["success"] = true;
    // $response["user_id"] = $user_id;
    // $response["user_pwd"] = $user_pwd;
    $response["success"] = true;
    array_push($response,
    array('userId'=>$user_id,
         'username'=>$username,
         'duty'=>$duty,
         'team'=>$team,
         'depart'=>$depart,
         'head'=>$head,
         'authority'=>$authority,
         'currentstate'=>$currentstate
     )); 
}

echo json_encode($response,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);

?>

