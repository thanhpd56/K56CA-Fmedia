<?php

require_once '../webpages/config.php';

class Database {

    var $_sql = '';
    var $_connection = '';
    var $_cursor = null;

    // khoi tao lop
    function Database($host, $user, $pass, $db) {
        $this->_connection = @mysql_connect($host, $user, $pass);
        if (!$this->_connection) {
            die("Could connect to mysql");
        }
        if ($db != '' && !mysql_select_db($db, $this->
                        _connection)) {
            die("Could not open database");
        }
    }

    // tao va gan cau lenh truy van
    function setQuery($sql) {
		$this->_sql = $sql;
    }

    // thuc thi cau lenh
    function query() {
        $this->_cursor = mysql_query($this->_sql, $this->
                _connection);
        return $this->_cursor;
    }

    // lay cac dong trong csdl va gan vao 1 mang
    function loadAllObject() {
        $array = array();

        if (!($cur = $this->query())) {
            return null;
        }
        while ($object = mysql_fetch_object($cur)) {
            $array[] = $object;
        }
        mysql_free_result($cur);

        return $array;
    }

    //lay 1 dong thoa man dieu kien va gan cho doi tuong.
    function loadAnObject() {
        if (!($cur = $this->query())) {
            return null;
        }
        $ret = null;
        if ($object = mysql_fetch_object($cur)) {
            $ret = $object;
        }
        mysql_free_result($cur);
        return $ret;
    }

    // ngat ket noi
    function disconnect() {
        mysql_close($this->_connection);
    }

}

class LinkModel {

    var $data;

    function LinkModel() {
        $this->data = new Database(HOST, USER, PASS, DB);
    }

    // list all links
    function listAllLink() {
        $this->data->setQuery("Select * from tblvideo");
        $result = $this->data->loadAllObject();
        return $result;
    }
    function listAllLinkLimited($startRecord, $display) {
        $this->data->setQuery("Select * from tblvideo Limit $startRecord, $display");
        $result = $this->data->loadAllObject();
        return $result;
    }
    function listAllLinkLimitedCatId($startRecord, $display, $cat_id) {
        $this->data->setQuery("Select "
                . "tblvideo.id, tblvideo.title, tblvideo.url, tblvideo.during_time, tblvideo.image_url, tblvideo.description, tblvideo.rating"
                . " from tblvideo Inner Join tblvideo_cat ON tblvideo.id = tblvideo_cat.video_id "
                . "WHERE tblvideo_cat.cat_id=$cat_id Limit $startRecord, $display");
        $result = $this->data->loadAllObject();
        return $result;
    }
    function listAllLinkFollowKeyword($key)
    {
        $this->data->setQuery("SELECT * FROM `tblvideo` WHERE title LIKE '%$key%'");
        $result = $this->data->loadAllObject();
        return $result;
    }
    function listAllLinkFollowCatId($cat_id) {
        $this->data->setQuery("SELECT DISTINCT tblvideo.id, tblvideo.title, tblvideo.url, tblvideo.during_time, tblvideo.image_url, tblvideo.description, tblvideo.rating FROM tblvideo INNER JOIN tblvideo_cat
                ON tblvideo.id= tblvideo_cat.video_id
                WHERE tblvideo_cat.cat_id='$cat_id'");
        $result = $this->data->loadAllObject();
        return $result;
    }

    // list detail of a link
    function listALink($id) {
        $this->data->setQuery("Select * from tblvideo where
				id= " . $id);
        $link = $this->data->loadAnObject();
        return $link;
    }

    function listAllCategory() {

        $this->data->setQuery("SELECT * FROM tblcategory");
        $result = $this->data->loadAllObject();
        return $result;
    }

    function deleteLink($id) {
        // khi xoa 
        $this->data->setQuery("DELETE FROM `tblvideo` WHERE id='$id'");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function insertLink($title, $url, $during_time, $image_url, $description, $rating, $time) {
        $time = (int) $time;
		$url = mysql_real_escape_string($url);
        $during_time = mysql_real_escape_string($during_time);
        $description = mysql_real_escape_string($description);
        $title = mysql_real_escape_string($title);
       
		//$during_time = (float) $during_time;
        $rating = (float) $rating;
        $this->data->setQuery("INSERT INTO `my_db`.`tblvideo` VALUES ('', '$title', '$url', '$during_time', '$image_url', '$description', $rating, $time)");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function updateLink($title, $url, $during_time, $image_url, $description, $rating, $id) {
        //$during_time = (float) $during_time;
		$url = mysql_real_escape_string($url);
        $during_time = mysql_real_escape_string($during_time);
        $description = mysql_real_escape_string($description);
        $title = mysql_real_escape_string($title);
        $rating = (float) $rating;
        $this->data->setQuery("UPDATE `tblvideo` SET `title`='$title',`url`='$url',`during_time`='$during_time' ,`image_url`='$image_url',`description`='$description',`rating`=$rating WHERE id=$id");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function insertVideoCat($video_id, $cat_id) {
        $video_id = (int) $video_id;
        $cat_id = (int) $cat_id;
        $this->data->setQuery("INSERT INTO `tblvideo_cat` VALUES ('','$video_id','$cat_id')");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function getVideoIdByTime($time) {
        $time = (int) $time;
        $this->data->setQuery("SELECT id FROM `tblvideo` WHERE create_time=$time");
        $result = $this->data->loadAnObject();
        return $result->id;
    }

    function getVideoById($id) {
        $id = (int) $id;
        $this->data->setQuery("SELECT * FROM tblvideo WHERE id=$id");
        $result = $this->data->loadAnObject();
        return $result;
    }

    function getCategoryByVideoId($video_id) {
        $video_id = (int) $video_id;
        $this->data->setQuery("SELECT * FROM tblvideo_cat WHERE video_id=$video_id");
        $result = $this->data->loadAllObject();
        return $result;
    }

    function deleteVideoCat($video_id) {
        $video_id = (int) $video_id;
        $this->data->setQuery("DELETE FROM `tblvideo_cat` WHERE video_id=$video_id");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function addCategory($cat_name) {
        $cat_name = mysql_real_escape_string($cat_name);
        $this->data->setQuery("INSERT INTO `tblcategory`VALUES ('','$cat_name')");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function deleteCategory($cat_id) {
        $cat_id = (int) $cat_id;
        $this->data->setQuery("DELETE FROM `tblcategory` WHERE id=$cat_id");
        if ($this->data->query())
            return 1;
        return 0;
    }

    function updateCategory($cat_id, $cat_name) {
        $cat_id = (int) $cat_id;
        $cat_name = mysql_real_escape_string($cat_name);
        $this->data->setQuery("UPDATE `tblcategory` SET `cat_name`='$cat_name' WHERE id = $cat_id");
        if ($this->data->query())
            return 1;
        return 0;
    }
    function checkPass($id, $pass)
    {
        $password_hash = md5($pass);
        $query = "Select * from users where id=$id && password='$password_hash'";
        if($query_run = mysql_query($query)){
            $num_rows = mysql_num_rows($query_run);
            if($num_rows > 0){
                return true;
            }  else {
                return false;
            }
        
        }
        return false;
    }
    function updatePassword($id, $new_pass)
    {
        $password_hash = md5($new_pass);
        $query = "UPDATE `users` SET `password`='$password_hash' WHERE id = $id";
        if($query_run = mysql_query($query))
        {
            return 1;
        }
        return 0;
    }
    function numVideos()
    {
        $query = "SELECT COUNT('id') as 'num_rows' FROM `tblvideo`";
        $query_run = mysql_query($query);
        $record = mysql_result($query_run, 0, 'num_rows');
        return $record;
        
    }
    function numVideosByCatId($cat_id)
    {
        $query = "SELECT COUNT(*) as 'num_rows' FROM `tblvideo` INNER JOIN tblvideo_cat ON tblvideo.id=tblvideo_cat.video_id WHERE tblvideo_cat.cat_id=$cat_id";
        $query_run = mysql_query($query);
        $record = mysql_result($query_run, 0, 'num_rows');
        return $record;
        
    }
    

}

?>