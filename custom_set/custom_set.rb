class CustomSet

  def initialize
    @empty = true
    @elements = []
    @size = 0
  end

  def is_empty?
    @empty
  end

  def add val
    @elements[size]= val
    @size += 1
    @empty = false
  end

  def size
    @size
  end

  def contains val
    index_of(val) >= 0
  end

  def remove val
    index = index_of(val)
    return unless index >= 0

    @elements[index] = @elements[size]
    @elements[size] = nil
    @size -= 1
  end

private

  def index_of val
    for i in (0..size)
      return i if @elements[i] == val
    end
    return -1
  end

end
