<?php

/**
 * Class to handle all db operations
 * This class will have CRUD methods for database tables
 *
 * @author Ravi Tamada
 * @link URL Tutorial link
 */
class DbHandler {

    private $conn;

    function __construct() {
        require_once dirname(__FILE__) . '/DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }

    public function getAllCategory() {
        $stmt = $this->conn->prepare("SELECT * From tblcategory");
        //$stmt->bind_param("i", $user_id);
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
    }
    public function getAllVideos(){
        $stmt = $this->conn->prepare("SELECT * FROM tblvideo");
        $stmt->execute();
        $videos = $stmt->get_result();
        $stmt->close();
        return $videos;
    }
    public function getAllVideosFollowCatId($cat_id){
        $stmt = $this->conn->prepare("SELECT DISTINCT tblvideo.id as id, tblvideo.title as title, tblvideo.url as url,
            tblvideo.during_time as during_time, tblvideo.image_url as image_url, tblvideo.description as description, 
            tblvideo.rating as rating, tblvideo.create_time as create_time
            FROM tblvideo INNER JOIN tblvideo_cat
                ON tblvideo.id= tblvideo_cat.video_id
                WHERE cat_id = ?
                ");
        $stmt->bind_param("i", $cat_id);
        $stmt->execute();
        $videos = $stmt->get_result();
        $stmt->close();
        return $videos;
    }
   
   
}

?>
