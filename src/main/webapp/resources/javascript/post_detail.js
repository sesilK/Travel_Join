        const detail = document.querySelector('.detail');
        const detailTitle = document.querySelector('.detail-title');
        const masterItems = document.querySelectorAll('.master-item');

        function select(selected){
            //Remove active class from all master-items
            for(var item of masterItems){
                item.classList.remove('active-item');
            }
            //Make selected tab active
            selected.classList.add('active-item');
            //Toggle the class that hides when the screen is medium size or less
            detail.classList.remove('hidden-md-down');
            //Set the content of the detail to the innerHTML of the selected item
            detailTitle.innerHTML = selected.innerHTML;
        }

        function back(){
            //Remove active class from all master-items
            for(var item of masterItems){
                item.classList.remove('active-item');
            }
            detail.classList.add('hidden-md-down');
        }