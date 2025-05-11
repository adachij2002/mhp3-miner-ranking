function showWorkingIndicator(data) {
	  showIndicatorRegion(data, "workingIndicator");
}

function showIndicatorRegion(data, regionId) {
	const el = document.getElementById(regionId);
	if (!el) {
		console.warn("Cannot find element: " + regionId);
		return;
	}

	if (data.status == "begin") {
		document.getElementById(regionId).style.display = "inline";
	} else if (data.status == "success") {
		document.getElementById(regionId).style.display = "none";
		$('a#prevTop,a#nextTop').addClass('ui-link');
	}
}
