const date = new Date();

const events = document.querySelectorAll(".events li");
const holidays = document.querySelectorAll(".holiday li");


var eventsList = [];
var holidayList = [];

for (let x = 0; x < events.length; x++) {
	var calEvent = {
		eventName: events[x].querySelector("#eventName").textContent,
		eventId: events[x].querySelector("#eventId").textContent,
		startTime: events[x].querySelector("#startTime").textContent,
		endTime: events[x].querySelector("#endTime").textContent
	};
	eventsList.push(calEvent);
}
for (let x = 0; x < eventsList.length; x++) {

	console.log(eventsList[x].eventId);
	console.log(eventsList[x].eventName);
	console.log(eventsList[x].startTime);
	console.log(eventsList[x].endTime);
}

for (let x = 0; x < holidays.length; x++) {
	var calHoliday = {
		holidayName: holidays[x].querySelector("#holidayName").textContent,
		holidayDate: holidays[x].querySelector("#holidayDate").textContent,
	};
	holidayList.push(calHoliday);
}
for (let x = 0; x < holidayList.length; x++) {

	console.log(holidayList[x].holidayName);
	console.log(holidayList[x].holidayDate);

}
	
const renderCalendar = () => {
	
    date.setDate(1);
	const monthDays = document.querySelector(".days");
	const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
	const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();
	const lastDayIndex = new Date(date.getFullYear(), date.getMonth()+1, 0).getDay();
	const nextDays = 7-lastDayIndex-1;
	const firstDayIndex = date.getDay();
	const months = [
	    "January",
	    "February",
	    "March",
	    "April",
	    "May",
	    "June",
	    "July",
	    "August",
	    "September",
	    "October",
	    "November",
	    "December"
	];
	
	document.querySelector(".date h1").innerHTML=months[date.getMonth()];
	document.querySelector(".date h3").innerHTML=date.getFullYear().toString();
	document.querySelector(".date p").innerHTML=new Date().toDateString();
	
	let days = "";

	//working logic
	for (let x = firstDayIndex; x > 0; x--) {
		let padMonth = (date.getMonth()).toString().padStart(2, "0");
		let padDay = (prevLastDay - x + 1).toString().padStart(2, "0");
		let padYear = date.getFullYear().toString();

		days += `<div class= "cal-cell"><ul>
                    <li>
                        <a href="/current-user/day/${padYear}-${padMonth}-${padDay}" class="prev-date">${padDay}</a>
                    </li>`
		//change
		for(let y = 0; y < holidayList.length; y++){
			if (holidayList[y].holidayDate.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <span class="holidays">${holidayList[y].holidayName}</span>
                    </li>`;
			}
		}
		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes(padYear +"-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	}
	
	for(let i =1; i<=lastDay; i++){

	    let padMonth = (date.getMonth()+1).toString().padStart(2,"0");
		let padDay = i.toString().padStart(2,"0");
		let padYear = date.getFullYear().toString();
		
	    if(i===new Date().getDate() && date.getMonth()=== new Date().getMonth()){
	      days += `<div class= "cal-cell"><ul>
                    <li>
                        <a href="/current-user/day/${padYear}-${padMonth}-${padDay}" class="today">${i}</a>
                    </li>`

		//change
		for(let y = 0; y < holidayList.length; y++){
			if (holidayList[y].holidayDate.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <span class="holidays">${holidayList[y].holidayName}</span>
                    </li>`;
			}
		}
		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	    }else{
			
	       days += `<div class= "cal-cell"><ul>
                    <li>
                        <a href="/current-user/day/${padYear}-${padMonth}-${padDay}" class="monthdays">${i}</a>
                    </li>`

		//change 
		for(let y = 0; y < holidayList.length; y++){
			if (holidayList[y].holidayDate.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <span class="holidays">${holidayList[y].holidayName}</span>
                    </li>`;
			}
		}
		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;     
        }
        
        
		if ((i+firstDayIndex)%7 === 0){
			days+=`<div class= "week-cell"><a href= "/current-user/week/${padYear}-${padMonth}-${padDay}" class = "viewweek">View Week</a></div>`;	
			
		}        
        
	}
	for(let j = 1; j<=nextDays; j++){
	    let padMonth = (date.getMonth()+2).toString().padStart(2,"0");
		let padDay = j.toString().padStart(2,"0");
		let padYear = date.getFullYear().toString();
	   days += `<div class= "cal-cell"><ul>
                    <li>
                        <a href="/current-user/day/${padYear}-${padMonth}-${padDay}" class="next-date">${j}</a>
                    </li>`

		//change
		for(let y = 0; y < holidayList.length; y++){
			if (holidayList[y].holidayDate.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <span class="holidays">${holidayList[y].holidayName}</span>
                    </li>`;
			}
		}
		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes(padYear + "-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}" class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	if ((j-nextDays)%7 === 0){
			days+=`<div class= "week-cell"><a href= "/current-user/week/${padYear}-${padMonth}-${padDay}" class = "viewweek">View Week</a></div>`;	
		
		}    
	}
	monthDays.innerHTML=days;
}


document.querySelector(".prev").addEventListener("click", ()=>{
    date.setMonth(date.getMonth()-1);
    renderCalendar();
});

document.querySelector(".next").addEventListener("click", ()=>{
    date.setMonth(date.getMonth()+1);
    renderCalendar();
});

renderCalendar();