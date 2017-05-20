using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    public interface ICategoryOperations
    {
        void Create(CategoryDTO category);
        IEnumerable<CategoryDTO> Get();
    }
}
