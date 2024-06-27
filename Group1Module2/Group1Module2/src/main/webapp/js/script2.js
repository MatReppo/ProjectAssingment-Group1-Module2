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