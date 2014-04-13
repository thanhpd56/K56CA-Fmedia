<?php

require_once '../include/DbHandler.php';
require_once '../include/PassHash.php';
require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

// User id from db - Global Variable
$user_id = NULL;

/**
 * Adding Middle Layer to authenticate every request
 * Checking if the request has valid api key in the 'Authorization' header
 */
function authenticate(\Slim\Route $route) {
    // Getting request headers
    $headers = apache_request_headers();
    $response = array();
    $app = \Slim\Slim::getInstance();

    // Verifying Authorization Header
    if (isset($headers['Authorization'])) {
        $db = new DbHandler();

        // get the api key
        $api_key = $headers['Authorization'];
        // validating api key
        if (!$db->isValidApiKey($api_key)) {
            // api key is not present in users table
            $response["error"] = true;
            $response["message"] = "Access Denied. Invalid Api key";
            echoRespnse(401, $response);
            $app->stop();
        } else {
            global $user_id;
            // get user primary key id
            $user_id = $db->getUserId($api_key);
        }
    } else {
        // api key is missing in header
        $response["error"] = true;
        $response["message"] = "Api key is misssing";
        echoRespnse(400, $response);
        $app->stop();
    }
}


/**
 * list all  category names
 */
$app->get('/showcat', function() {
            //global $user_id;
            $response = array();
            $db = new DbHandler();

            // fetching all category
            $result = $db->getAllCategory();

            $response["error"] = false;
            $response["cats"] = array();

            // looping through result and preparing tasks array
            while ($task = $result->fetch_assoc()) {
                $tmp = array();
                $tmp["id"] = $task["id"];
                $tmp["cat_name"] = $task["cat_name"];
               array_push($response["cats"], $tmp);
            }

            echoRespnse(200, $response);
        });
$app->get('/all_videos', function(){
    $response = array();
    $db = new DbHandler();
    // fetching all video info
    $result = $db->getAllVideos();
    $response["error"] = false;
    $response["videos"] = array();
    
    // looping each element to get video
    while($video = $result->fetch_assoc()){
        $tmp = array();
        $tmp["id"] = $video["id"];
		$tmp["title"] = $video["title"];
		$tmp["url"] = $video["url"];
        $tmp["during_time"] = $video["during_time"];
        $tmp["image_url"] = $video["image_url"];
        $tmp["description"] = $video["description"];
        $tmp["rating"] = $video["rating"];
        $tmp["create_time"] = $video["create_time"];
        array_push($response["videos"], $tmp);
    }
    echoRespnse(200, $response);
    
});
$app->get('/cat/:id', function($cat_id){
    $response = array();
    $db = new DbHandler();
    // fetching all video info
    $result = $db->getAllVideosFollowCatId($cat_id);
    $response["error"] = false;
    $response["videos"] = array();
    
    // looping each element to get video
    while($video = $result->fetch_assoc()){
        $tmp = array();
        $tmp["id"] = $video["id"];
		$tmp["title"] = $video["title"];
		$tmp["url"] = $video["url"];
        $tmp["during_time"] = $video["during_time"];
        $tmp["image_url"] = $video["image_url"];
        $tmp["description"] = $video["description"];
        $tmp["rating"] = $video["rating"];
        $tmp["create_time"] = $video["create_time"];
        array_push($response["videos"], $tmp);
    }
    echoRespnse(200, $response);
    
});



function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();
?>