
<?php

require_once '../Controller/linkController.php';
$linkControl = new linkController();
if (isset($_GET['cat_id'])) {
    $cat_id = $_GET['cat_id'];
    $result = $linkControl->model->deleteCategory($cat_id);
    // when insert successfully
    if ($result == 1) {
        // DO DU LIEU VE TRANG MANAGE CATEGORY
        $allCats = $linkControl->model->listAllCategory();
        foreach ($allCats as $cat) {
                                  echo ' 
                                  <div class="form-group" style="width:300px; position:relative;">
                                   
                                    <input style="float:left; margin:10px;" type="text" class="form-control" id="title" name="title" 
                                    value="'.$cat->cat_name.'">
                                 </div> 
                                 <button style="float:left; margin:8px;" class="btn btn-danger">Delete</button>
                                 <button style="float:left; margin:8px;" class="btn btn-info">Update</button>
                            ';
                              }
    } else {
        echo "<script>alert('Delete Fail!');</script>";
    }
} else {
    echo "<script>alert('Delete Fail!');</script>";
}

//$cat_name = $_GET['cat_name'];
//$parent_id = $_GET['parent_id'];
?>
	