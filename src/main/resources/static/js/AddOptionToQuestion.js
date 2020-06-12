var optionCounter = 2;

function addOption(){

//      Generate IDs
      var optionTextId = "optionText"+ optionCounter;
      var optionValueId = "optionValue"+ optionCounter;
      optionCounter++;
//      Display Option Text
      var divDisplayText = document.createElement('div');
      divDisplayText.setAttribute("class","col-6 input-group p-1");
      var newOptionTextLabel = document.createElement('label');
      newOptionTextLabel.setAttribute('for',optionTextId)
      newOptionTextLabel.innerHTML = "Display Text";
      var newOptionText = document.createElement('input');
      newOptionText.setAttribute("type", "text");
      newOptionText.setAttribute("class", "form-control ml-2");
      newOptionText.setAttribute("id", optionTextId);
      newOptionText.setAttribute("placeholder", "Option Text");
      divDisplayText.append(newOptionTextLabel);
      divDisplayText.append(newOptionText);
//      Stored As for Option
      var divOptionValue = document.createElement('div');
      divOptionValue.setAttribute("class","col-6 input-group p-1");
      var newOptionValueLabel = document.createElement('label');
      newOptionValueLabel.setAttribute('for',optionTextId)
      newOptionValueLabel.innerHTML = "Stored As";
      var newOptionValue = document.createElement('input');
      newOptionValue.setAttribute("type", "number");
      newOptionValue.setAttribute("class", "form-control ml-2");
      newOptionValue.setAttribute("id", optionValueId);
      newOptionValue.setAttribute("placeholder", "Option Value");
      divOptionValue.append(newOptionValueLabel);
      divOptionValue.append(newOptionValue);
      $('#options').append(divDisplayText);
      $('#options').append(divOptionValue);
    }
function remove(){
  var last_chq_no = $('#total_chq').val();
  if(last_chq_no>1){
    $('#new_'+last_chq_no).remove();
    $('#total_chq').val(last_chq_no-1);
  }
}