/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com). */
jQuery(function($){
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
		'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
		monthNamesShort: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
		'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '',
		yearRange:'1990:+10',
		changeMonth: true,
		changeYear: true,
		buttonText:"달력",
		showOn:"focus",
		showOtherMonths: true,
		selectOtherMonths: true,
		showButtonPanel:true,
		autoSize:true
		};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	
	/*옵셥 설정
	 * $('#datepicker').datepicker({
        showOn: 'both', // focus / button / both
        showAnim: 'fadeIn', // slideDown/fadeIn/blind/bounce/clip/drop/fold/slide
        showOptions: {}, // Options for enhanced animations
        defaultDate: null, // +/-number for offset from today, null for today
        appendText: '', // Display text following the input box, e.g. showing the format
        buttonText: '달력입니다', // Text for trigger button
        buttonImage: 'calendar.gif', //버튼이미지에 사용할 이미지 경로
        buttonImageOnly: false, // 버튼만 누를 경우 달력 표시 true/false
        hideIfNoPrevNext: false, // True to hide next/previous month links
        navigationAsDateFormat: false, // True if date formatting applied to prev/today/next links
        gotoCurrent: false, // True if today link goes back to current selection instead
        changeMonth: false, // True if month can be selected directly, false if only prev/next
        					//달별로 선택 할 수 있다
        changeYear: false, // True if year can be selected directly, false if only prev/next
        					//년별로 선택 할 수 있다.
        yearRange: 'c-10:c+10', // Range of years to display in drop-down,
            // either relative to today's year (-nn:+nn), relative to currently displayed year
            // (c-nn:c+nn), absolute (nnnn:nnnn), or a combination of the above (nnnn:-n)
        showOtherMonths: true, // 나머지 날짜도 화면에 표시, 선택은 불가
        selectOtherMonths: false, // 나머지 날짜에도 선택을 하려면 true
        showWeek: false, // True to show week of the year, false to not show it
        calculateWeek: this.iso8601Week, // How to calculate the week of the year,
            // takes a Date and returns the number of the week for it
        shortYearCutoff: '+10', // Short year values < this are in the current century,
            // > this are in the previous century,
            // string value starting with '+' for current year + value
        minDate: null, // The earliest selectable date, or null for no limit
        		'-1M',   //오늘 부터 3달전까지만 선택 할 수 있다.
        maxDate: null, // +0d 하면 오늘 이후는 선택못함, null 은 제한없음
        		'+36M',   //오늘 부터 36개월후까지만 선택 할 수 있다.
        duration: 'fast', // Duration of display/closure
        beforeShowDay: null, // Function that takes a date and returns an array with
            // [0] = true if selectable, false if not, [1] = custom CSS class name(s) or '',
            // [2] = cell title (optional), e.g. $.datepicker.noWeekends
        beforeShow: null, // Function that takes an input field and
            // returns a set of custom settings for the date picker
        onSelect: null, // Define a callback function when a date is selected
        onChangeMonthYear: null, // Define a callback function when the month or year is changed
        onClose: null, // Define a callback function when the datepicker is closed
        numberOfMonths: 1, // Number of months to show at a time
        					//오늘부터 3달치 달력
        showCurrentAtPos: 0, // The position in multipe months at which to show the current month (starting at 0)
        stepMonths: 1, // Number of months to step back/forward
        stepBigMonths: 12, // Number of months to step back/forward for the big links
        altField: '', // Selector for an alternate field to store selected dates into
        altFormat: '', // The date format to use for the alternate field
        constrainInput: true, // The input is constrained by the current date format
        showButtonPanel: true, // True to show button panel, false to not show it
        autoSize: false, // True to size the input for the date format, false to leave as is
        clearText: '-', // -의 문자는 삭제
        defaultStatus:'',
    });*/
});