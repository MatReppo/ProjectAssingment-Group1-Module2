document.addEventListener('DOMContentLoaded', function() {
    // Add event listener to checkboxes
    var checkboxes = document.querySelectorAll('.class-checkbox');
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            var className = this.getAttribute('data-class');
            var row = this.parentNode.parentNode;
            if (this.checked) {
                row.classList.add('checked');
            } else {
                row.classList.remove('checked');
            }
        });
    });
});

function editbtn() {
    // Simulate submission process
    alert('Edit Mode');
    
    // Redirect to the new home page
    window.location.href = 'JPT.html';
}

function deletebtn(button) {
    alert('File Deleted');
    
    // Find the row containing the button that was clicked
    var row = button.closest('tr');
    // Remove the row from the table
    row.remove();
}
