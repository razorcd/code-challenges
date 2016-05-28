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
    case
    when (nr==0)
      "zero"
    when (nr>0 && nr<10)
      digit_to_english(nr)
    when (nr>=10 && nr<20)
      ten_19_to_english(nr)
    when (nr>=20 && nr<30)
      "twenty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=30 && nr<40)
      "thirty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=40 && nr<50)
      "forty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=50 && nr<60)
      "fifty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=60 && nr<70)
      "sixty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=70 && nr<80)
      "seventy"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=80 && nr<90)
      "eighty"+ digit_to_english(nr%10, with_hyphen: true)
    when (nr>=90 && nr<100)
      "ninety"+ digit_to_english(nr%10, with_hyphen: true)
    else
      raise "#{digit} does not exist"
    end
  end

  def digit_to_english digit, with_hyphen: false
    digit_s= case digit.to_i
      when 0
        ""
      when 1
        "one"
      when 2
        "two"
      when 3
        "three"
      when 4
        "four"
      when 5
        "five"
      when 6
        "six"
      when 7
        "seven"
      when 8
        "eight"
      when 9
        "nine"
      else
        raise "#{digit} does not exist"
      end
    with_hyphen ? "-#{digit_s}" : digit_s
  end

  def ten_19_to_english nr
    t= ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
    t[nr%10]
  end
end
