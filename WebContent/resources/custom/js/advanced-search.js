/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var defaultMainSearchRows = [
    ['numero-ocorrencia', 'periodo-registro'], 
    ['nome-pessoa', 'tipo-pessoa'],
    ['periodo-fato', 'placa-veiculo']
];

var DROPPOINT_El = "<div class='droppoint'/>";
var SELECTOR_WRAPPER = "<div class='selector-wrapper'/>";
var DROPPOINT_ROW = "<div class='search-row' />";
var ADVANCED_SEARCH_SCOPE = 'advanced_search';
var IS_DROPPOINT = 'IS_DROPPOINT';
var IS_DROPAREA =  'IS_DROPAREA';
    
function AdvancedSearch(){
    
    
    this._setMainSearchRows = function(mainSearchRows){
        var strMainSearchRows = JSON.stringify(mainSearchRows);
        localStorage.setItem('mainSearchRows', strMainSearchRows);
    }
    
    this._getMainSearchRows = function(){

        var mainSearchRows = localStorage.getItem('mainSearchRows');
        
        if(!mainSearchRows){
             return defaultMainSearchRows;
        }
        
        return JSON.parse(mainSearchRows);
    }
    
    this._storeMainSearchRow = function(){
        var mainSearchRows = [];
        $('.main-search > .search-row').each(function(){
            var searchFields = [];
            $(this).children('.search-field').each(function(){
                if(this.style.position !== 'absolute'){
                    var searchFieldId = this.getAttribute('sf-id');
                    searchFields.push(searchFieldId);
                }
            })
            mainSearchRows.push(searchFields);
        })
        
        this._setMainSearchRows(mainSearchRows);
    }
    
    this._turnIntoDroppable = function(element, type){
        
        var handleDrop;
        var accept;
        
        if(type === IS_DROPPOINT){
            handleDrop = this._handleDroppointDrop;
            accept = '*';
        }else{
            handleDrop = this._handleDropareaDrop;
            accept = '.main-search .search-field';
        }
        
        $(element).droppable({
            activeClass: 'droppoint-active',
            hoverClass: 'droppoint-hover',
            tolerance: 'pointer',
            accept: accept,
            scope: ADVANCED_SEARCH_SCOPE,
            drop: handleDrop.bind(this)
        });
    }
    
    
    this._handleDroppointDrop = function(event, ui){

        var dragged = ui.draggable[0],
        droppoint = event.target,
        newDroppoint = $(DROPPOINT_El)[0];

        if($(dragged).parents('.dropparea').length > 0){
            this._createDummyClone(dragged);
        }
        
        $(dragged).next('.droppoint').remove();
        droppoint.after(dragged, newDroppoint);

        this._turnIntoDroppable(newDroppoint, IS_DROPPOINT);
        
        var droppointParent = $(droppoint).parent();
        this._cleanMainUselessRows();
        this._storeMainSearchRow();
        this._handleNextRowCreation(droppointParent);    
    }
    
    this._handleDropareaDrop = function(event, ui){
        var dragged = ui.draggable[0],
        droppoint = event.target,
        searchFieldId = dragged.getAttribute('sf-id');

        $(dragged).next('.droppoint').remove();
        
        var dummyClone = $(droppoint).find('[sf-id="'+searchFieldId+'"]');
        dummyClone.replaceWith(dragged);
        
        this._cleanMainUselessRows();
        this._storeMainSearchRow();
    }
    
    this._cleanMainUselessRows = function(){
          
        $('.main-search > .search-row').each(function(){   
            if($(this).children().length <= 2){
                $(this).remove();
            }
        })
       
    };
    
    this._handleNextRowCreation = function(droppedRow){
        
        var newLastNextRow = this.createNewMainSearchRow();
        
        if(newLastNextRow){
           var newDroppoint = $(DROPPOINT_El)[0];
           this._turnIntoDroppable(newDroppoint, IS_DROPPOINT);
           newLastNextRow.prepend(newDroppoint);
        }
        
    };
    
    this._createDummyClone = function (element){
        var clone = $(element).clone();
        clone.addClass('search-disabled');
        $(element).before(clone);
    }
    
    this.createNewMainSearchRow = function(){
        var lastRow = $('.main-search > .search-row').last(),
        lastRowChildrensCount = $(lastRow).children().length;
        
        if(lastRowChildrensCount > 0){
            lastRow.after(DROPPOINT_ROW);
            return lastRow.next();
        }
        
    }

    this.createSelectorWrappers = function(){
        $('.advanced-search .search-field > input').wrap(SELECTOR_WRAPPER);
    }


    this.createDroppoints = function (){
        $('.advanced-search .main-search > .search-row').prepend(DROPPOINT_El);
        $('.advanced-search .main-search .search-field').after(DROPPOINT_El);
//        $('.advanced-search .secondary-search .search-field').after(DROPPOINT_El);
    }

    this.initDragAndDrop = function(){
        var turnIntoDroppable = this._turnIntoDroppable.bind(this);
        
        $('.advanced-search .search-field:not(.search-disabled)').draggable({
            helper: 'clone',
            scope: ADVANCED_SEARCH_SCOPE,
            zIndex: ++PrimeFaces.zindex
         });

        $('.advanced-search .droppoint').each(function() {
            turnIntoDroppable(this, IS_DROPPOINT);
        });
        
        $('.advanced-search .dropparea').each(function() {
            turnIntoDroppable(this, IS_DROPAREA);
        });
     }
     
     this.placeMainSearchFields = function(){
         var createDummyClone = this._createDummyClone.bind(this),
         mainSearch = $('.advanced-search .main-search'),
         mainSearchRows = this._getMainSearchRows();
         
         mainSearchRows.forEach(function(rowFields){ //Loop nas linhas
             
             mainSearch.append(DROPPOINT_ROW);
             rowFields.forEach(function (searchFieldId){//Loop nos Ids
                var origSearchField = $('.secondary-search [sf-id="'+searchFieldId +'"]');
                
                createDummyClone(origSearchField);
                
                mainSearch.children().last().append(origSearchField);
             });
             
         });
     }

}

 $(function() {
    var advancedSearch = new AdvancedSearch();
    
    advancedSearch.placeMainSearchFields();
    
    advancedSearch.createSelectorWrappers();
    advancedSearch.createNewMainSearchRow();
    advancedSearch.createDroppoints();
    
    advancedSearch.initDragAndDrop();
 });
 
 function highlightDefaultFields(){
   var defaultFieldsIds = [].concat.apply([], defaultMainSearchRows);
   
   defaultFieldsIds.forEach((searchFieldId) => {
        if('tipo-pessoa' !== searchFieldId){
            var searchField = $('.advanced-search [sf-id="'+searchFieldId +'"]');
            searchField.addClass('with-error');
        }
   })
}

function removeHigightFromFields(){
    $('.search-field.with-error').each(function (){
        $(this).removeClass('with-error');
    })
}