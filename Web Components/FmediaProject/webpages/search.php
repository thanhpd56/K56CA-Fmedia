<?php
    require_once '../Controller/linkController.php';
    $linkControl = new linkController();
    if(isset($_GET['key']))
    {
        $key = $_GET['key'];
        $linkControl->showAllLinkFollowKeyWord($key);
    }

?>