using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    public interface ICategoryOperastions
    {
        void Create(CategoryDTO category);
        IEnumerable<CategoryDTO> Get();
    }
}
