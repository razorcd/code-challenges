class AnagramPairs
  def initialize s= ""
    @s= s
    @sub= []
  end

  def count
    for i in (0..@s.length-2)
      for j in (i+1..@s.length-1)
        load_anagrams_starting_at i: i, j: j
      end
    end

    @sub.uniq.length
  end

private

  def load_anagrams_starting_at i:, j:
    for len in (1..(@s.length - j))
      # puts "i: #{i}, j: #{j}, len: #{len}.  Compare: #{s[i,len]} & #{s[j, len]} =       #{s[i,len].chars.sort.join == s[j, len].chars.sort.join}"
      if strings_with_same_chars?(@s[i,len], @s[j, len])
        @sub << [[i, len], [j, len]]
      end
    end
  end

  def strings_with_same_chars? s1, s2
    s1.chars.sort == s2.chars.sort
    # s1.each_char do |c|
    #   good=false
    #   (s2.length).times do |j|
    #     if c == s2[j]
    #       s2[j]= ""
    #       good=true
    #       break
    #     end
    #   end
    #   return false unless good
    # end
    # return true
  end

end
