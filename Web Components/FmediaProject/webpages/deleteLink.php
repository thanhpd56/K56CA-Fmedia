<?php
    require './connect.inc.php';
    require './core.inc.php';
    if(!loggedin())
    {
        header('location: loginPage.php');
    }
    require_once '../Controller/linkController.php';
    $linkControl = new linkController();
    
?>
<?php


if (isset($_GET['id'])) {
    $link_id = $_GET['id'];
    // delete link
    {
        // delete on catlink table
        //$linkControl->model->deleteLink($link_id);

        if ($linkControl->model->deleteLink($link_id)==1)
        {
           // DELETE SUCCESSFULLY
            echo "<script>alert('Delete successfully!'); location.href='index.php';</script>";
            //header('location:../webpages/');
        
        }else
        {
            // DELETE FAIL
            echo "<script>alert('Delete Fail!'); location.href='index.php';</script>";
            
        }
        // delete on link table	
    }
} else
    echo 'loi isset';
?>