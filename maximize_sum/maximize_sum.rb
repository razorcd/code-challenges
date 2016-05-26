class MaximizeSum
  def initialize arr
    @arr= arr
  end

  def biggest_subarray_sum_modulo modulo
    subarrays.map {|s| s.inject(&:+) % modulo}.max
  end

private

  def subarrays
    subarr= []
    for i in (0..@arr.length-1)
      for len in (1..@arr.length-i)
        subarr << @arr.slice(i, len)
      end
    end
    subarr
  end
end
