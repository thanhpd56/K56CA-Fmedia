<?php
$thumbOfRow = 4;
?>

<?php
$counter = 0;
echo '
		<div class="row" id="div_thumb" >
		  
	';
foreach ($links as $link) {
    $del_link = 'deleteLink.php?id=' . $link->id;
    $edit_link = 'editLink.php?id=' . $link->id;
    $counter++;
    if ($counter % $thumbOfRow == 0) {
        // print row
        echo '
				<div class="row" >
			';
    }
    echo '
				<div class="col-sm-6 col-md-3" >
					<div class="thumbnail">
					  <img class="thumb" style="" src="' . $link->image_url . '" alt="...">
					  <div class="caption">
						<h4>' . substr($link->title, 0, 20) . '</h4>
						';
    ?>

    <p>
        <a id="edit" href="<?php echo $edit_link; ?>"
           class="btn btn-primary" role="button">Edit</a> 
        <a id="delete" class="btn btn-default" role="button"
           onclick="return confirm('Are you sure to delete?')"
           href="<?php echo $del_link; ?>"
           >Delete</a>
    </p>
    <?php
    echo'
                      </div>
					</div>
				  </div>
			
			';

    if ($counter % $thumbOfRow == 0) {
        // print row
        echo '
				</div>
			';
    }
}
?>










