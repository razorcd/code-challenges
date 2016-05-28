class EnglishNumbers
  def initialize number
    @number= number
  end

  def to_english
    english_nr= ""
    number= @number

    loop do
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
    return digit_to_english(nr) if (nr>=0 && nr<10)
    return ten_19_to_english(nr) if (nr>=10 && nr<20)
    return t[nr/10] if (nr%10==0)
    t[nr/10]+ "-"+ digit_to_english(nr%10)
  end

  def ten_19_to_english nr
    t= ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
    t[nr%10]
  end

  def digit_to_english digit
    d= ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    d[digit]
  end
end
