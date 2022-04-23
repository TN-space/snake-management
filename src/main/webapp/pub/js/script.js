
import slick from "./slick";

// for edit button in showSnakes
$('#snake-edit-btn').click(function(){
    $('#snakes-container-id').hide();
    $('#snake-form').show();
})

// for edit button in showFeeders
$('#feeder-edit-btn').click(function(){
    $('#feeders-container').hide();
    $('#feeder-form').show();
})

// for table in showFeedings
$(function(){
    $('#keywords').tablesorter();
})
