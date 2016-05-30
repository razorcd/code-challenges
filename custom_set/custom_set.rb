class CustomSet

  def initialize
    @elements = []
    @size = 0
  end

  def is_empty?
    size == 0
  end

  def add element
    return if contains(element)

    @elements[size] = element
    @size += 1
    @empty = false
  end

  def size
    @size
  end

  def contains element
    index_of(element) >= 0
  end

  def remove element
    index = index_of(element)
    return unless index >= 0

    @elements[index] = @elements[size]
    @elements[size] = nil
    @size -= 1
  end

private

  def index_of element
    for i in (0..size)
      return i if @elements[i] == element
    end
    return -1
  end

end
