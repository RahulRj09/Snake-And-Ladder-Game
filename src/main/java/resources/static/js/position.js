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
    50: "fifty"
}

$('#dice').click(function () {
    $('#position').empty();
    $('#currentPositionEmailId').empty();
    $.emailId = localStorage.getItem("emailId");
    $.get("/getCurrentPosition?emailId=" + $.emailId, function (data) {
        if (data) {
            let position = data.details[0].position;
            let emailId = data.details[0].emailId;
            if (emailId === $.emailId) {
                $('#' + positions[position]).prepend('<img id="players" src="../static/images/jerry.png"  alt="you"/>')
            }
            if (emailId === "computer@gmail.com") {
                $('#' + positions[position]).prepend('<img id="players" src="../static/images/tom.png"  alt="you"/>')
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