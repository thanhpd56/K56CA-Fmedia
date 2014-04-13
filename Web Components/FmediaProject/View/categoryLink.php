<?php

$thumbOfRow = 4;
?>

<?php

$counter = 0;
echo '
		<div class="row">
		  
	';
foreach ($links as $link) {
    $counter++;
    if ($counter % $thumbOfRow == 0) {
        // print row
        echo '
				<div class="row">
			';
    }
    echo '
				<div class="col-sm-6 col-md-2">
					<div class="thumbnail">
					  <img src="../images/meo.jpg" alt="...">
					  <div class="caption">
						<h3>' . $link->link_title . '</h3>
						<p>' . $link->link_desc . '</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">Edit</a> 
							<a href="#" class="btn btn-default" role="button">Delete</a>
						</p>
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










