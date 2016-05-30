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
    for i in (0..size)
      return true if @elements[i] == val
    end
    return false
  end
end
