class EnglishNumbers
  def initialize number
    @number= number
  end

  def to_english
    return "zero" if @number==0
    english_nr= []
    number= @number.abs

    groups_of_3_digits do |digits_group, index|
      next if digits_group==0
      english_nr.unshift group_name(index)
      english_nr.unshift *group_of_3_digits_to_english(digits_group)
    end

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
    gr= [nil, "thousand", "million", "billions", "trillions"]
    gr[group]
  end

  def group_of_3_digits_to_english nr
    raise "#{nr} is over hundreds" if nr>999
    nr_en= []

    tents_en= tents_to_english(nr%100)
    nr_en.unshift(tents_en)
    hundreds_en= hundreds_to_english(nr/100)
    nr_en.unshift(hundreds_en)

    nr_en
  end

  def hundreds_to_english nr
    return nil if nr==0
    digit_to_english(nr)+ " "+ "hundred"
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
    d= [nil, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    d[digit]
  end
end
