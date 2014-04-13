// JavaScript Document
function createXmlHttpRequestObject(){
   var xmlHttp;
   
   if(window.ActiveXObject){
      try{
         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
         }catch(e){
            xmlHttp =false;
            }
      }else{
         try{
            xmlHttp= new XMLHttpRequest();
            }catch(e){
               xmlHttp =false;
               }
         }
      if(!xmlHttp)
            alert("cant create that object hoss!");
      else
            return xmlHttp;
   }
var xmlHttp= createXmlHttpRequestObject();

function getType(strURL) {		
	
	if (xmlHttp) {
		
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				// only if "OK"
				if (xmlHttp.status == 200) {						
					document.getElementById('type_div').innerHTML=xmlHttp.responseText;		
				} else {
					alert("There was a problem while using XMLHTTP:\n" + xmlHttp.statusText);
				}
			}				
		}			
		xmlHttp.open("GET", strURL, true);
		xmlHttp.send(null);
	}
			
}
function searchKey(strURL) {		
	
	if (xmlHttp) {
		
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				// only if "OK"
				if (xmlHttp.status == 200) {						
					document.getElementById('main_links').innerHTML=xmlHttp.responseText;		
				} else {
					alert("There was a problem while using XMLHTTP:\n" + xmlHttp.statusText);
				}
			}				
		}			
		xmlHttp.open("GET", strURL, true);
		xmlHttp.send(null);
	}
			
}
function insertLink(strURL) {		
	
	if (xmlHttp) {
		
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				// only if "OK"
				if (xmlHttp.status == 200) {						
					document.getElementById('result').innerHTML=xmlHttp.responseText;		
				} else {
					alert("There was a problem while using XMLHTTP:\n" + xmlHttp.statusText);
				}
			}				
		}			
		xmlHttp.open("GET", strURL, true);
		xmlHttp.send(null);
	}
			
}
function inputWord(link, thediv){
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
	}
	
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById(thediv).innerHTML = xmlhttp.responseText;
			
			
		}
	}
	xmlhttp.open('GET', link, true);
	xmlhttp.send();
			
}
var notif = '';
var error;
function findError(link, callback){
				var xmlhttp;
				if(window.XMLHttpRequest){
					xmlhttp = new XMLHttpRequest();
				} else {
					xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
				}
				
				xmlhttp.onreadystatechange = function(){
					if(xmlhttp.readyState==4 && xmlhttp.status==200){
						callback(xmlhttp.responseText);
					}
				}
				xmlhttp.open('GET', link, false);
				xmlhttp.send();
			
						
			
			}
var m;			
function doSomething(){
	var file = document.upload.file.value;
	var img_name = document.upload.img_name.value;
	var id_danhmuc = document.upload.id_danhmuc.value;
	var id_danhmuc_chitiet = document.upload.id_danhmuc_chitiet.value;
	var img_width = document.upload.img_width.value;
	var img_height = document.upload.img_height.value;
	var img_price = document.upload.img_price.value;
	var img_status = document.upload.img_status.value;
	var img_description = document.upload.img_description.value;
	var img_sale = document.upload.img_sale.value;					
	var link = 'include/valid_form.php?'+'img_name='+img_name +'&id_danhmuc='+id_danhmuc +'&id_danhmuc_chitiet='+id_danhmuc_chitiet +
		'&img_width='+img_width +'&img_height='+img_height +'&img_price='+img_price +'&img_sale='+img_sale +'&img_status='+img_status +
		'&img_description='+img_description +'&file='+file;

	findError(link, function (response) {
            m = response;
			
        });
	alert(m);
	if(m=='Tải ảnh lên thành công!')
	return true;
	else{
		return false;
	}
}

function updateImage(){
	var file = document.upload.file.value;
	var img_name = document.upload.img_name.value;
	var id_danhmuc = document.upload.id_danhmuc.value;
	var id_danhmuc_chitiet = document.upload.id_danhmuc_chitiet.value;
	var img_width = document.upload.img_width.value;
	var img_height = document.upload.img_height.value;
	var img_price = document.upload.img_price.value;
	var img_sale = document.upload.img_sale.value;
	var img_status = document.upload.img_status.value;
	var img_description = document.upload.img_description.value;
	
	if(file=='') file = 'custom.jpg';
	var link = 'include/valid_form_update.php?'+'img_name='+img_name +'&id_danhmuc='+id_danhmuc +'&id_danhmuc_chitiet='+id_danhmuc_chitiet +
		'&img_width='+img_width +'&img_height='+img_height +'&img_price='+img_price+'&img_sale='+img_sale +'&img_status='+img_status +
		'&img_description='+img_description +'&file='+file;

	findError(link, function (response) {
            m = response;
			
        });
	alert(m);
	if(m=='Update ảnh thành công!')
	return true;
	else{
		return false;
	}
}


 
 
 
 
 
 