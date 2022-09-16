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
<<<<<<< HEAD
	
const renderCalendar = () => {
	
    date.setDate(1);
=======



const renderCalendar = () => {
	date.setDate(1);
>>>>>>> b4b521d6673f69499dcd015f81565e48bda076f7
	const monthDays = document.querySelector(".days");
	const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
	const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();
	const lastDayIndex = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDay();
	const nextDays = 7 - lastDayIndex - 1;
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
<<<<<<< HEAD
	
	document.querySelector(".date h1").innerHTML=months[date.getMonth()];
	
	document.querySelector(".date p").innerHTML=new Date().toDateString();
	
	let days = "";

	//working logic
	for (let x = firstDayIndex; x > 0; x--) {
		let padMonth = (date.getMonth()).toString().padStart(2, "0");
		let padDay = (prevLastDay - x + 1).toString().padStart(2, "0");

		days += `<div><ul>
                    <li>
                        <a href="/day/2022-${padMonth}-${padDay}" class="monthdays">${padDay}</a>
                    </li>`

		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes("2022-" + padMonth + "-" + padDay)) {
				days += `<li>
                        <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	}
	//working logic
	
	for(let i =1; i<=lastDay; i++){

	    let padMonth = (date.getMonth()+1).toString().padStart(2,"0");
		let padDay = i.toString().padStart(2,"0");
		
	    if(i===new Date().getDate() && date.getMonth()=== new Date().getMonth()){
	      days += `<div><ul>
                    <li>
                        <a href="/day/2022-${padMonth}-${padDay}" class="monthdays">${i}</a>
                    </li>`

		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes("2022-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	    }else{
			
	       days += `<div><ul>
                    <li>
                        <a href="/day/2022-${padMonth}-${padDay}" class="monthdays">${i}</a>
                    </li>`

		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes("2022-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}"class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;     
        }
        
        
		if ((i+firstDayIndex)%7 === 0){
			days+=`<div><a href= "/week/2022-${padMonth}-${padDay}" class = "viewweek">View Week</a></div>`;	
		
		}        
        
	}
	for(let j = 1; j<=nextDays; j++){
	    let padMonth = (date.getMonth()+2).toString().padStart(2,"0");
		let padDay = j.toString().padStart(2,"0");
	   days += `<div><ul>
                    <li>
                        <a href="/day/2022-${padMonth}-${padDay}" class="monthdays">${j}</a>
                    </li>`

		for (let z = 0; z < eventsList.length; z++) {
			if (eventsList[z].startTime.toString().includes("2022-" + padMonth + "-" + padDay)) {
				days += `<li>
                       <a href="/event-overview/?id=${eventsList[z].eventId}" class="events">${eventsList[z].eventName}</a>
                    </li>`;
			}
		}
		days += `</ul></div>`;
	if ((j-nextDays)%7 === 0){
			days+=`<div><a href= "/week/2022-${padMonth}-${padDay}" class = "viewweek">View Week</a></div>`;	
		
		}    
=======

	document.querySelector(".date h1").innerHTML = months[date.getMonth()];

	document.querySelector(".date p").innerHTML = new Date().toDateString();

	let days = "";

	for (let x = firstDayIndex; x > 0; x--) {
		days += `<div class="prev-date">${prevLastDay - x + 1}</div>`
	}
	for (let i = 1; i <= lastDay; i++) {


		//if(i===new Date().getDate() && date.getMonth()=== new Date().getMonth()){

		//    days += `<div class="today">${i}</div>`;
		//}else{
		//    days += `<div>${i}</div>`;
		//}
		let padMonth = (date.getMonth() + 1).toString().padStart(2, "0");
		let padDay = i.toString().padStart(2, "0");

		if (i === new Date().getDate() && date.getMonth() === new Date().getMonth()) {
			days += `<a href="/day/2022-${padMonth}-${padDay}"><div class="today">${i}</div></a>`;
		} else {

			days += `<a href="/day/2022-${padMonth}-${padDay}"><div>${i}</div></a>`;
		}


		if ((i + firstDayIndex) % 7 === 0) {
			days += `<div><a href= "/week/2022-${padMonth}-${padDay}">View Week</a></div>`;

		}

	}
	for (let j = 1; j <= nextDays; j++) {
		days += `<div class="next-date">${j}</div> `
	}
	if (nextDays !== 0) {
		//TODO: Fix week view for trailing days...

		//		days+=`<div><a href= "/week/2022-${(date.getMonth()+1).toString().padStart(2,"0")}-${nextDays}">View Week</a></div>`;	
>>>>>>> b4b521d6673f69499dcd015f81565e48bda076f7
	}
	monthDays.innerHTML = days;
}


document.querySelector(".prev").addEventListener("click", () => {
	date.setMonth(date.getMonth() - 1);
	renderCalendar();
});

document.querySelector(".next").addEventListener("click", () => {
	date.setMonth(date.getMonth() + 1);
	renderCalendar();
});

renderCalendar();