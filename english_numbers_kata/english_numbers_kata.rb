class EnglishNumbers
  def initialize number
    @number= number
  end

  def to_english
    english_nr= ""

    loop do
      number= @number
      tent= number% 100
      number= (number/100).to_i
      english_nr+= tents_to_english(tent)
      break if number==0
    end
    english_nr
  end

private

  def tents_to_english nr
    t= [nil, nil, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
    return "zero" if nr==0
    return digit_to_english(nr) if (nr<10)
    return ten_19_to_english(nr) if (nr>=10 && nr<20)
    t[nr/10]+ digit_to_english(nr%10, with_hyphen: true)
  end

  def ten_19_to_english nr
    t= ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
    t[nr%10]
  end

  def digit_to_english digit, with_hyphen: false
    d= ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    digit_s= d[digit]
    with_hyphen ? "-#{digit_s}" : digit_s
  end
end
