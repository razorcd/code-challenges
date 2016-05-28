class EnglishNumbers
  def initialize number
    @number= number
  end

  def to_english
    return "zero" if @number==0
    english_nr= []
    number= @number

    loop do
      tent= number% 100
      number= (number/100).to_i
      english_nr.unshift(tents_to_english(tent))
      break if number==0

      over_hundred= number% 10
      number= (number/10).to_i
      english_nr.unshift(over_hundreds_to_english(over_hundred, 0))
      break if number==0

      over_hundred= number% 10
      number= (number/10).to_i
      english_nr.unshift(over_hundreds_to_english(over_hundred, 1))
      break if number==0
    end
    puts english_nr.to_s
    english_nr.reject(&:empty?).join(" ")  #.map(&:strip)
  end

private

  def over_hundreds_to_english digit, group= 0
    oh= ["hundred", "thousand", "million", "billion"]
    return "" if digit==0
    digit_to_english(digit)+ " "+ oh[group]
  end

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
    d= ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    d[digit]
  end
end
