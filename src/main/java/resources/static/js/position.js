let positions = {
    1: "one",
    2: "two",
    3: "three",
    4: "four",
    5: "five",
    6: "six",
    7: "seven",
    8: "eight",
    9: "nine",
    10: "ten",
    11: "eleven",
    12: "twelve",
    13: "thirteen",
    14: "fourteen",
    15: "fifteen",
    16: "sixteen",
    17: "seventeen",
    18: "eighteen",
    19: "nineteen",
    20: "twenty",
    21: "twentyOne",
    22: "twentyTwo",
    23: "twentyTree",
    24: "twentyFour",
    25: "twentyFive",
    26: "twentySix",
    27: "twentySeven",
    28: "twentyEight",
    29: "twentyNine",
    30: "thirty",
    31: "thirtyOne",
    32: "thirtyTwo",
    33: "thirtyThree",
    34: "thirtyFour",
    35: "thirtyFive",
    36: "thirtySix",
    37: "thirtySeven",
    38: "thirtyEight",
    39: "thirtyNine",
    40: "fourty",
    41: "fourtyOne",
    42: "fourtyTwo",
    43: "fourtyTree",
    44: "fourtyFour",
    45: "fourtyFive",
    46: "fourtySix",
    47: "fourtySeven",
    48: "fourtyEight",
    49: "fourtyNine",
    50: "fifty",
    51: "fiftyOne",
    52: "fiftyTwo",
    53: "fiftyThree",
    54: "fiftyFour",
    55: "fiftyFive",
    56: "fiftySix",
    57: "fiftySeven",
    58: "fiftyEight",
    59: "fiftyNine",
    60: "sixty",
    61: "sixtyOne",
    62: "sixtyTwo",
    63: "sixtyThree",
    64: "sixtyFour",
    65: "sixtyFive",
    66: "sixtySix",
    67: "sixtySeven",
    68: "sixtyEight",
    69: "sixtyNine",
    70: "seventy",
    71: "seventyOne",
    72: "seventyTwo",
    73: "seventyThree",
    74: "seventyFour",
    75: "seventyFive",
    76: "seventySix",
    77: "seventySeven",
    78: "seventyEight",
    79: "seventyNine",
    80: "eighty",
    81: "eightyOne",
    82: "eightyTwo",
    83: "eightyThree",
    84: "eightyFour ",
    85: "eightyFive ",
    86: "eightySix",
    87: "eightySeven",
    88: "eightyEight",
    89: "eightyNine",
    90: "ninety",
    91: "ninetyOne",
    92: "ninetyTwo",
    93: "ninetyThree",
    94: "ninetyFour",
    95: "ninetyFive",
    96: "ninetySix",
    97: "ninetySeven",
    98: "ninetyEight",
    99: "ninetyNine",
    100: "OneHundred"
};

$('#dice').click(function () {
    $('#position').empty();
    $('#currentPositionEmailId').empty();
    $.emailId = localStorage.getItem("emailId");
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        if (data) {
            let position = data.details[0].position;
            let emailId = data.details[0].emailId;
            if (emailId === $.emailId) {
                $('div >  img.jerry').remove();
                $('#board').prepend('<div id="' + positions[position] + '">' +
                    '<img class="jerry" id="players" src="../static/images/jerry.png"  alt="you"/></div>')
            }
            if (emailId === "computer@gmail.com") {
                $('div >  img.tom').remove();
                $('#board').prepend('<div id="' + positions[position] + '">' +
                    '<img class="tom" id="players" src="../static/images/tom.png"  alt="Computer"/></div>')
            }
            $('#currentPositionEmailId').append(emailId);
            $('#position').append(position);
            if (position === 100) {
                $.get("/getWinner", function (data) {
                    console.log(data)
                    $('#winner').append("Winner " + data);
                });
            }
        }
    });
});
