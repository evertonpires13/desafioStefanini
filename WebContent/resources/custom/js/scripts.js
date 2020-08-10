/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function smoothScrollTop(){
    $('html, body').animate({ scrollTop: 0 }, 'slow');
}


function toogleCollaplse(targetId, origin){
    var target = $('.collapse#'+ targetId);
    
    if(target.hasClass('in')){
        target.removeClass('in');
        $(origin).find('.fa').switchClass('fa-minus', 'fa-plus');
    }else{
        target.addClass('in');
        $(origin).find('.fa').switchClass('fa-plus', 'fa-minus');
    }
}

function validateInt(event){
    if(event.which < 48 || event.which > 57) return false;
}

// DataScroller
function hideRowFromDataScroller(index){
    var childSelector = ':nth-child(' + (index+1) + ')';
    $('.ui-datascroller-item'+childSelector).remove()
}

function textToUpperCase(origin){
    origin.value = origin.value.toUpperCase();
}

function appendToUrl(toAppend, url){
    var urlForAppend = url? url : window.location.pathname;
    window.history.pushState('','', urlForAppend + toAppend);
}

function mapValueToOtherInput(thisInputEl, otherInputId){
    document.getElementById(otherInputId).value = thisInputEl.value;
}


//Ao carregar pagina o botao de voltar ao topo é chamada
jQuery(document).ready(function() {
	var isTop = $(window).scrollTop() === 0;
    var offset = 220;
    var duration = 500;
    $(window).scroll(function() {
        if ($(this).scrollTop() > offset) {
            $('#toTop').fadeIn(duration);
        } else {
            $('#toTop').fadeOut(duration);
        }
    });
    jQuery('#toTop').click(function(event) {
        event.preventDefault();
        $('html, body').animate({scrollTop: 20}, duration);
        return false;
    })
});

(function(){
    
    PrimeFaces.locales['pt_BR'] = {
                closeText: 'Fechar',
                prevText: 'Anterior',
                nextText: 'Próximo',
                currentText: 'Começo',
                monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
                dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
                dayNamesMin: ['D','S','T','Q','Q','S','S'],
                weekHeader: 'Semana',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: '',
                timeOnlyTitle: 'Só Horas',
                timeText: 'Tempo',
                hourText: 'Hora',
                minuteText: 'Minuto',
                secondText: 'Segundo',
                currentText: 'Data Atual',
                ampm: false,
                month: 'Mês',
                week: 'Semana',
                day: 'Dia',
                allDayText : 'Todo Dia'
            };
    
    $('.ui-widget-overlay.ui-dialog-mask').on( "click", () => console.log('okok'));
    
    $(window).scroll(function () {
        var isTop = $(window).scrollTop() === 0;	

        if(!isTop){   	
            $('.marquee').hide();
        }else{
            $('.marquee').show();
        }
      
    });
    
})();