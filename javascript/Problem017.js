//
// If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
//
// If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
//
//
// NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.



var sumAll = function (start, end) {
  numbers = {
    0: '',
    1: 'one',
    2: 'two',
    3: 'three',
    4: 'four',
    5: 'five',
    6: 'six',
    7: 'seven',
    8: 'eight',
    9: 'nine',
    10: 'ten',
    11: 'eleven',
    12: 'twelve',
    13: 'thirteen',
    14: 'fourteen',
    15: 'fifteen',
    16: 'sixteen',
    17: 'seventeen',
    18: 'eighteen',
    19: 'nineteen',
    20: 'twenty',
    30: 'thirty',
    40: 'forty',
    50: 'fifty',
    60: 'sixty',
    70: 'seventy',
    80: 'eighty',
    90: 'ninety',
    100: 'hundred',
    1000: 'thousand',
  }


  function words1to20(num) {
    if(num > 20) throw "wrong argument";
    return numbers[num];
  }

  function words21to99(num) {
    if(num < 21 || num > 99) throw "wrong argument";
    var r = num % 10;
    return numbers[num - r] + " " + numbers[r];
  }

  function words1to99(num) {
    if(num > 99) throw "wrong argument";

    if(num <= 20) return words1to20(num);
    else return words21to99(num);
  }

  function words100to999(num) {
    if(num < 100 || num > 999) throw "wrong argument";

    var twoDigits = num % 100;
    var wordsTwoDigit = twoDigits === 0 ? "" : " and " + words1to99(twoDigits);

    var hundredDigit = Math.floor(num / 100)
    return numbers[hundredDigit] + " hundred" + wordsTwoDigit;
  }

  function words1to999(num, needPrefix) {
    if(num > 999) throw "wrong argument";

    if(num <= 99) return (needPrefix ? "and ": "") + words1to99(num);
    else return words100to999(num);
  }

  function words1000to9999(num) {
    if(num < 1000 || num > 9999) throw "wrong argument";

    var threeDigits = num % 1000;
    var wordsThreeDigit = threeDigits === 0 ? "" :  words1to999(threeDigits, threeDigits < 100);

    var thousandDigit = Math.floor(num / 1000)
    return numbers[thousandDigit] + " thousand " + wordsThreeDigit;
  }

  function words1to9999(num) {
    if(num > 9999) throw "wrong argument";

    if(num <= 999) return words1to999(num);
    else return words1000to9999(num);
  }

  var array = [];
  for(var i = start; i <= end; i++) {
    array.push(words1to9999(i));
  }

  return array.map(el => el.replace(/\s/g, "").length).reduce((a, b) => a + b);
}


console.log("Solution is", sumAll(1, 1000));
