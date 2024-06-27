document.addEventListener('DOMContentLoaded', () => {
    const subDropdownBtn = document.querySelector('.subdropbtn');
    const subDropdownContent = document.querySelector('.sub-dropdown-content');

    subDropdownBtn.addEventListener('click', (e) => {
        e.stopPropagation(); // Prevent event from bubbling up to parent elements
        subDropdownContent.style.display = subDropdownContent.style.display === 'block' ? 'none' : 'block';
    });

    // Close the sub-dropdown if clicking outside of it
    document.addEventListener('click', (e) => {
        if (!subDropdownBtn.contains(e.target)) {
            subDropdownContent.style.display = 'none';
        }
    });
});