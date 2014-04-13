<?php
require_once '../Controller/linkController.php';
//include '../View/style.php';
$linkControl = new linkController();
$display = 12;
// find number of page
if (isset($_GET['page']) and (int) $_GET['page'] > 0) {
    $page = $_GET['page'];
} else {
    // if page is not setted so we need to calculate it else page= $_GET['page'];
    // find number of record/rows
    $record = $linkControl->getNumRecord();
    if ($record > $display) {//if record>display: page = ceil of record/display
        $page = ceil($record / $display);
    } else {//else page =1;
        $page = 1;
    }
}

// if start is setted then start_record = GET['start'] else start =0// begin page;
if (isset($_GET['start'])) {
    $start_record = $_GET['start'];
} else {
    $start_record = 0;
}



//current page:  current = start_record/display+1
$current_page = $start_record / $display + 1;
// next_record = start_record + display
$next_start_record = $start_record + $display; // for next button; 
//previous = start_record - display
$previous_record = $start_record - $display; // for previous button;
//last_start_record = ($page - 1)* display
$last_start_record = ($page - 1) * $display;
// for current >7
if ($current_page >= 7) {
    $start_page = $current_page - 3;
    $end_page = $current_page + 3;
    if ($page > $current_page + 3) { // for current < page -3
        $end_page = $current_page + 3;
    } else if ($current_page <= $page) {// for current>=$page-3
        $start_page = $page - 6;
        $end_page = $page;
    }
} else {// for current <7
    if ($page > 7) {// for num_page>7
        $end_page = 7;
        $start_page = 1;
    } else {// for num_page<=7
        $end_page = $page;
        $start_page = 1;
    }
}

//-------------//
$links = $linkControl->model->listAllLinkLimited($start_record, $display);
$counter = 0;
$thumbOfRow = 4;
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

$last_link = "index.php?page=" . $page . '&start=' . $last_start_record;
$first_link = "index.php?page=" . $page . '&start=0';
$previous_link = "index.php?page=" . $page . '&start=' . $previous_record;
$next_link = "index.php?page=" . $page . '&start=' . $next_start_record;
?>
<div class="row"></div>
<div class="row" style="text-align: right;">
     <ul class="pagination">
        <?php
        if ($page > 1) {
            if ($current_page > 1) {// special for this: first, previous button
                ?>	<li><a href="<?php echo $first_link; ?>">First</a></li>
                <li><a href="<?php echo $previous_link; ?>">Previous</a></li>
                <?php
            }// common
            for ($i = $start_page; $i <= $end_page; $i++) {
                if ($i == $current_page) {
                    ?>
                    <li><a><?php echo $i; ?></a></li>
                    <?php
                } else {
                    $link = "index.php?page=" . $page . '&start=' . (($i - 1) * $display);
                    ?>
                    <li><a href="<?php echo $link; ?>"><?php echo $i; ?></a></li>

                    <?php
                }
            }

            if ($current_page < $page) {
                ?>
                <li> <a href="<?php echo $next_link; ?>">Next</a></li>
                <li><a href="<?php echo $last_link; ?>">Last</a></li>
                <?php
            }
        }
        ?>
    </ul>
</div>