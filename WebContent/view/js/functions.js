function showWorkingIndicator(data) {
	  showIndicatorRegion(data, "workingIndicator");
}

function showIndicatorRegion(data, regionId) {
	if (data.status == "begin") {
		document.getElementById(regionId).style.display = "inline";
	} else if (data.status == "success") {
		document.getElementById(regionId).style.display = "none";
		$('a#prevTop,a#nextTop').addClass('ui-link');
	}
}
