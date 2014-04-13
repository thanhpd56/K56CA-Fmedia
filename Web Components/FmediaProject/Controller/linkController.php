<?php

include '../Model/linkModel.php';

class linkController {

    public $model;

    public function __construct() {
        $this->model = new linkModel();
    }

    //public function showAllLink()
    public function showAllLinks() {
        if (isset($_GET['link_id'])) {
            // DO SHOW DETAIL OF LINK
        } else
        if (isset($_GET['cat_id'])) {
            // DO SHOW ALL LINKS BELONG CAT_ID
            //$links = $this->model->listAllLinkFollowCatId($_GET['cat_id']);
            include '../View/paginationviewCatId.php';
        } else {
            // DO SHOW ALL LINK
            include '../View/paginationview.php';
        }
    }

    function showAllLinkFollowKeyWord($key) {
        $links = $this->model->listAllLinkFollowKeyword($key);
        include '../View/allLinkView.php';
    }

    public function showALink() {
        if (isset($_GET['link_id'])) {
            $link = $this->model->listALink($_GET['link_id']);
            include '../View/aLinkView.php';
        }
    }

    public function showNavigationBar() {
        include '../View/mainCategory_navigationBar.php';
    }

    public function showSideBar() {
        $cates = $this->model->listAllCategory();
        include '../View/sideBar.php';
    }

    public function insertVideo() {
        $linkControll = new linkController();

        if (isset($_POST['title']) &&
                isset($_POST['url']) &&
                isset($_POST['during_time']) &&
                isset($_POST['image_url']) &&
                isset($_POST['description']) &&
                isset($_POST['rating']) &&
                isset($_POST['category']) &&
                isset($_POST['category1']) &&
                isset($_POST['category2']) &&
                isset($_POST['category3']) &&
                isset($_POST['category4'])
        ) {
            $time = time();
            $title = $_POST['title'];
            $url = $_POST['url'];
            $during_time = $_POST['during_time'];
            $image_url = $_POST['image_url'];
            $description = $_POST['description'];
            $rating = (float) $_POST['rating'];

            $time = time();
            $category[0] = (int) $_POST['category'];
            $category[1] = (int) $_POST['category1'];
            $category[2] = (int) $_POST['category2'];
            $category[3] = (int) $_POST['category3'];
            $category[4] = (int) $_POST['category4'];
            $success = true;
            if ($linkControll->model->insertLink($title, $url, $during_time, $image_url, $description, $rating, $time) == 1) {
                // INSERT VIDEO SUCCESSFULLY
                // NEXT UPDATE TABLE VIDEO_CAT
                $video_id = (int) $this->model->getVideoIdByTime($time);
                for ($i = 0; $i < 5; $i++) {
                    $isRepeat = false;
                    if ($category[$i] != -1) {
                        for ($j = $i; $j >= 0; $j--) {
                            //NEU GIA TRI NAY DA TUNG CO THI KHONG THEM NUA
                            if ($i != $j && $category[$i] == $category[$j])
                                $isRepeat = true;
                        }
                        // NEU DUOC CHON + KHONG LAP
                        if (!$isRepeat) {
                            if (!$this->model->insertVideoCat($video_id, $category[$i]) == 1)
                                $success = false;
                        }
                    }
                }
                if ($success)
                    echo "<script>alert('Insert successfully!'); location.href='insertLink.php';</script>";
                else {
                    echo "<script>alert('Insert Fail!');</script>";
                }
            } else {
                // INSERT FAIL
                echo "<script>alert('Insert Fail. Query error!');</script>";
            }
        }
    }

    public function updateVideo($id) {
        $linkControll = new linkController();

        if (isset($_POST['title']) &&
                isset($_POST['url']) &&
                isset($_POST['during_time']) &&
                isset($_POST['image_url']) &&
                isset($_POST['description']) &&
                isset($_POST['rating']) &&
                isset($_POST['category']) &&
                isset($_POST['category1']) &&
                isset($_POST['category2']) &&
                isset($_POST['category3']) &&
                isset($_POST['category4'])
        ) {
            $time = time();
            $title = $_POST['title'];
            $url = $_POST['url'];
            $during_time = $_POST['during_time'];
            $image_url = $_POST['image_url'];
            $description = $_POST['description'];
            $rating = (float) $_POST['rating'];
            $category[0] = (int) $_POST['category'];
            $category[1] = (int) $_POST['category1'];
            $category[2] = (int) $_POST['category2'];
            $category[3] = (int) $_POST['category3'];
            $category[4] = (int) $_POST['category4'];
            $success = true;
            if ($linkControll->model->updateLink($title, $url, $during_time, $image_url, $description, $rating, $id) == 1) {
                // INSERT VIDEO SUCCESSFULLY


                $video_id = (int) $id;
                // DELETE ALL OLD VIDEO_CAT
                $linkControll->model->deleteVideoCat($video_id);
                // NEXT UPDATE TABLE VIDEO_CAT
                for ($i = 0; $i < 5; $i++) {
                    $isRepeat = false;
                    if ($category[$i] != -1) {
                        for ($j = $i; $j >= 0; $j--) {
                            //NEU GIA TRI NAY DA TUNG CO THI KHONG THEM NUA
                            if ($i != $j && $category[$i] == $category[$j])
                                $isRepeat = true;
                        }
                        // NEU DUOC CHON + KHONG LAP
                        if (!$isRepeat) {
                            if (!$this->model->insertVideoCat($video_id, $category[$i]) == 1)
                                $success = false;
                        }
                    }
                }
                if ($success)
                    echo "<script>alert('Update successfully!'); location.href='index.php';</script>";
                else {
                    echo "<script>alert('Update Fail1!');</script>";
                }
            } else {
                // INSERT FAIL
                echo "<script>alert('Update Fail2!');</script>";
            }
        }
    }

    function checkPassword($id, $pass) {
        
    }

    function changePassword($id) {
        if (isset($_POST['old_pass']) && isset($_POST['new_pass']) && isset($_POST['re_new_pass'])) {
            $old_pass = $_POST['old_pass'];
            $new_pass = $_POST['new_pass'];
            $re_new_pass = $_POST['re_new_pass'];
            if ($this->model->checkPass($id, $old_pass)) {
                // DO UPDATE PASSWORD
                $result = $this->model->updatePassword($id, $new_pass);
                if ($result == 1) {
                    // THONG BAO THANH CONG
                    echo "<script>alert('Change password successfully!');</script>";
                } else {
                    // THONG BAO CHANGE THAT BAI
                    echo "<script>alert('Change password fail!');</script>";
                }
            } else {
                // THONG BAO SAI PASSWORD
                echo "<script>alert('Invalid password!');</script>";
            }
        }
    }
    public function getNumRecord(){
        return $this->model->numVideos();
    }
    public function getNumRecordByCatId($cat_id){
        return $this->model->numVideosByCatId($cat_id);
    }
    public function showPagination()
    {
        include '../View/paginationview.php';
    }

}

?>