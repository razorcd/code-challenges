class FixedSizeArray
  def initialize size
    @array= Array.new(size)
    @mem_size= size
  end

  def [] index
    validate index: index
    @array[index]
  end

  def []= index, val
    validate index: index
    @array[index]= val
  end

  def length
    @mem_size
  end

  def inspect
    @array.inspect
  end

private

  def validate index:
    raise "index out of bound" if (index >= @mem_size) || (index < 0)
  end
end
