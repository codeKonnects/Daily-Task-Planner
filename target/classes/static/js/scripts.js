checkboxes = Array.from(document.querySelectorAll('.taskDone'));
checkboxes.forEach(function(checkbox, i) {
    checkbox.onchange = function() {

        $.ajax({
            url: './editTaskDone/'+ this.value,
            type: 'POST',
            beforeSend: function() { checkbox.disabled = true; },
            complete: function() { checkbox.disabled = false; },
            success: function(response) {
                console.log(response);
            }
        });
    }
});