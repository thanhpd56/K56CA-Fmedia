<div class="col-md-2" id="leftCol">

    <div class="well"> 
        <ul class="nav nav-stacked" id="sidebar">
            <?php
            foreach ($cates as $cat) {
                if (isset($_GET['cat_id']) && $_GET['cat_id'] == $cat->id)
                    echo '<li class="active" ><a class="active" href="index.php?cat_id=' . $cat->id . '"">' . $cat->cat_name .''. '</a></li>';
                else
                    echo '<li><a href="index.php?cat_id=' . $cat->id . '"">' . $cat->cat_name . '</a></li>';
            }
            ?>
        </ul>
    </div>

</div>  
