class BiggerIsGreater
  def initialize word
    @word= word
  end

  def greater
    solution= nil
    combinations.each do |w|
      solution= w if (w > @word && (solution.nil? || w < solution))
    end
    solution
  end

private

  def combinations
    @combinations= []
    @new_word= []
    permutations @word.chars
  end

  def permutations arr
    for i in (0..arr.length-1)
      ch= arr.slice!(i)
      @new_word.push(ch)
      if (arr.length==0) then @combinations.push(@new_word.join) end
      permutations arr
      arr.insert(i, ch)
      @new_word.pop
    end
    @combinations
  end

end
