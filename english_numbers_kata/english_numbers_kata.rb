class EnglishNumbers
  ZERO= "zero"
  DIGITS= [nil, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
  TEN_TO_NINETEEN= ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
  TENTS= [nil, nil, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
  HUNDRED= "hundred"
  GROUPS= [nil, "thousand", "million", "billions", "trillions"]
  MINUS= "minus"

  def initialize number
    @number= number.abs
    @negative= (number<0 ? true : false)
  end

  def to_english
    return EnglishNumbers::ZERO if @number==0
    english_nr= []
    number= @number

    groups_of_3_digits do |digits_group, index|
      next if digits_group==0
      english_nr.unshift group_name(index)
      english_nr.unshift *group_of_3_digits_to_english(digits_group)
    end

    english_nr.unshift(EnglishNumbers::MINUS) if @negative
    english_nr.compact.join(" ")
  end

private

  def groups_of_3_digits
    nr= @number
    index=0
    loop do
      yield nr%1000, index
      nr= (nr/1000).to_i
      break if nr==0
      index+=1
    end
  end

  def group_name group= 0
    EnglishNumbers::GROUPS[group]
  end

  def group_of_3_digits_to_english nr
    nr_en= []

    tents_en= tents_to_english(nr%100)
    nr_en.unshift(tents_en)
    hundreds_en= hundreds_to_english(nr/100)
    nr_en.unshift(hundreds_en)

    nr_en
  end

  def hundreds_to_english nr
    return nil if nr==0
    digit_to_english(nr)+ " "+ EnglishNumbers::HUNDRED
  end

  def tents_to_english nr
    return digit_to_english(nr) if (nr>=0 && nr<10)
    return ten_19_to_english(nr) if (nr>=10 && nr<20)
    return EnglishNumbers::TENTS[nr/10] if (nr%10==0)
    EnglishNumbers::TENTS[nr/10]+ "-"+ digit_to_english(nr%10)
  end

  def ten_19_to_english nr
    EnglishNumbers::TEN_TO_NINETEEN[nr%10]
  end

  def digit_to_english digit
    EnglishNumbers::DIGITS[digit]
  end
end
