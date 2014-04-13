<?php

if ($link != null) {
    echo 'Title: ' . $link->link_title . '</br>';
    echo 'Url: ' . $link->link_video . '</br>';
    echo 'Thumb: ' . $link->link_thumb . '</br>';
    echo 'Description: ' . $link->link_desc . '</br>';
    echo 'Rate: ' . $link->link_rating . '</br>';
    echo 'Source Id: ' . $link->link_sourceId . '</br>';
    echo 'Time: ' . $link->link_created_at . '</br>';
}
?>